package com.makiia.gatewayservice.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {
    private String path;
    private String method;

    // Constructor con parámetros
    /*public RequestDto(String path, String method) {
        this.path = path;
        this.method = method;
    }

    // Getters y Setters
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    // Constructor por defecto (opcional)
    public RequestDto() {}
    */

}
