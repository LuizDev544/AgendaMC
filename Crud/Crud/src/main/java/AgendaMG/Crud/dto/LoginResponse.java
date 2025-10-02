package AgendaMG.Crud.dto;

import java.util.Map;

public class LoginResponse {
    private String message;
    private String token;
    private String role;
    private String usuario;
    private boolean authenticated;

    // Construtores
    public LoginResponse() {}

    public LoginResponse(String message, String token, String role, String usuario, boolean authenticated) {
        this.message = message;
        this.token = token;
        this.role = role;
        this.usuario = usuario;
        this.authenticated = authenticated;
    }

    // Método para criar response de sucesso
    public static LoginResponse success(String token, String role, String usuario) {
        return new LoginResponse(
            "Login realizado com sucesso",
            token,
            role,
            usuario,
            true
        );
    }

    // Método para criar response de erro
    public static LoginResponse error(String message) {
        return new LoginResponse(
            message,
            null,
            null,
            null,
            false
        );
    }

    // Método para converter para Map (mantendo compatibilidade)
    public Map<String, Object> toMap() {
        return Map.of(
            "message", message,
            "token", token,
            "role", role,
            "usuario", usuario,
            "authenticated", authenticated
        );
    }

    // Getters e Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}