package com.makiia.userservice.security;

import com.makiia.userservice.dto.RequestDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

@Component
@ConfigurationProperties(prefix = "admin-paths")
public class RouteValidator {

    private List<RequestDto> pathsAdmin;
    private List<RequestDto> pathsUser;

    public List<RequestDto> pathsAdmin() {
        return pathsAdmin;
    }
    public List<RequestDto> pathsUser() {
        return pathsUser;
    }
    public void setPathsAdmin(List<RequestDto> pathsAdmin) {
        this.pathsAdmin = pathsAdmin;
    }
    public void setPathsUser(List<RequestDto> pathsUser) {
        this.pathsUser = pathsUser;
    }
    public boolean isAdminPath(RequestDto dto) {
        return pathsAdmin.stream().anyMatch(p ->
                Pattern.matches(p.getUri(), dto.getUri()) && p.getMethod().equals(dto.getMethod()));
    }

    public boolean isUserPath(RequestDto dto) {
        return pathsUser.stream().anyMatch(p ->
                Pattern.matches(p.getUri(), dto.getUri()) && p.getMethod().equals(dto.getMethod()));
    }

}
