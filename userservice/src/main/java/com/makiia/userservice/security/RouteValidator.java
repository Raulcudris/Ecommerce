package com.makiia.userservice.security;

import com.makiia.userservice.dto.RequestDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

@Component
@ConfigurationProperties(prefix = "route-paths")
public class RouteValidator {

    private List<RequestDto> adminPaths;
    private List<RequestDto> userPaths;

    public List<RequestDto> getAdminPaths() {
        return adminPaths;
    }

    public void setAdminPaths(List<RequestDto> adminPaths) {
        this.adminPaths = adminPaths;
    }

    public List<RequestDto> getUserPaths() {
        return userPaths;
    }

    public void setUserPaths(List<RequestDto> userPaths) {
        this.userPaths = userPaths;
    }

    public boolean isAdminPath(RequestDto dto) {
        return adminPaths.stream().anyMatch(p ->
                Pattern.matches(p.getUri(), dto.getUri()) && p.getMethod().equals(dto.getMethod()));
    }

    public boolean isUserPath(RequestDto dto) {
        return userPaths.stream().anyMatch(p ->
                Pattern.matches(p.getUri(), dto.getUri()) && p.getMethod().equals(dto.getMethod()));
    }

}
