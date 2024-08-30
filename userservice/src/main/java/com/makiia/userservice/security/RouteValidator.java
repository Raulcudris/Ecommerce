package com.makiia.userservice.security;

import com.makiia.userservice.dto.RequestDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

@Component
@ConfigurationProperties(prefix = "admin-paths")
public class RouteValidator {

    private List<RequestDto> paths;

    public List<RequestDto> paths() {
        return paths;
    }
    public void setPaths(List<RequestDto> pathsAdmin) {
        this.paths = paths;
    }
    public boolean isAdmin(RequestDto dto) {
        return paths.stream().anyMatch(p ->
                Pattern.matches(p.getUri(), dto.getUri()) && p.getMethod().equals(dto.getMethod()));
    }

}
