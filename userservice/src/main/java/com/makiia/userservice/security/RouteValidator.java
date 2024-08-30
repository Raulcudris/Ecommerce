package com.makiia.userservice.security;

import com.makiia.userservice.dto.RequestDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

@Component
@ConfigurationProperties(prefix = "route")

public class RouteValidator {

    private List<Path> adminPaths;
    private List<Path> userPaths;

    public List<Path> getAdminPaths() {
        return adminPaths;
    }

    public void setAdminPaths(List<Path> adminPaths) {
        this.adminPaths = adminPaths;
    }

    public List<Path> getUserPaths() {
        return userPaths;
    }

    public void setUserPaths(List<Path> userPaths) {
        this.userPaths = userPaths;
    }

    public boolean isAdminPath(RequestDto dto) {
        return adminPaths.stream().anyMatch(p ->
                dto.getUri().matches(p.getUri()) && dto.getMethod().equals(p.getMethod()));
    }

    public boolean isUserPath(RequestDto dto) {
        return userPaths.stream().anyMatch(p ->
                dto.getUri().matches(p.getUri()) && dto.getMethod().equals(p.getMethod()));
    }

    // Inner class to map the paths
    public static class Path {
        private String uri;
        private String method;

        public String getUri() {
            return uri;
        }
        public void setUri(String uri) {
            this.uri = uri;
        }
        public String getMethod() {
            return method;
        }
        public void setMethod(String method) {
            this.method = method;
        }
    }

}
