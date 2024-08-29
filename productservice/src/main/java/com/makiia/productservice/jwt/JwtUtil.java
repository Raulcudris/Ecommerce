package com.makiia.productservice.jwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.List;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;
    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public Claims getAllClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }
    public String getSubject(String token) {
        Claims claims = getAllClaims(token);
        return (claims != null) ? claims.getSubject() : null;
    }
    public List<String> getRoles(String token) {
        Claims claims = getAllClaims(token);
        return (claims != null) ? claims.get("roles", List.class) : null;
    }

}
