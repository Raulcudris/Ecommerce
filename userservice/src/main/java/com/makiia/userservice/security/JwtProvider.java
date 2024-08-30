package com.makiia.userservice.security;
import com.makiia.userservice.dto.RequestDto;
import com.makiia.userservice.entity.EntityUsers;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {
    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private RouteValidator routeValidator;

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createToken(EntityUsers entityUsers) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", entityUsers.getId());
        claims.put("role", entityUsers.getRole());
        Date now = new Date();
        Date exp = new Date(now.getTime() + 3600000); // 1 hour

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(entityUsers.getUsername())
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean validate(String token, RequestDto dto) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return false;
        }
        String role = claims.get("role", String.class);
        if ("admin".equals(role) && routeValidator.isAdminPath(dto)) {
            return true;
        }
        if ("user".equals(role) && routeValidator.isUserPath(dto)) {
            return true;
        }

        return false;
    }

    public String getUserNameFromToken(String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
        } catch (Exception e) {
            return "bad token";
        }
    }
}
