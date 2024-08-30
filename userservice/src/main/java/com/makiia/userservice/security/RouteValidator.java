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

    public void setPaths(List<RequestDto> paths) {
        this.paths = paths;
    }

    public boolean isAdminPath(RequestDto dto) {
        return paths.stream().anyMatch(p ->
                Pattern.matches(p.getUri(), dto.getUri()) && p.getMethod().equals(dto.getMethod()));
    }

    public boolean isPublicPath(RequestDto dto) {
        // Aquí podrías agregar lógica para identificar rutas accesibles a todos
        // Por ejemplo, podrías definir otra lista de rutas accesibles a todos y verificarlas aquí.
        return true; // Supongamos que todas las rutas son públicas si no están en admin-paths.
    }
}
