package AgendaMG.Crud.dto;

public class TokenValidationRequest {
    private String token;

    // Construtores
    public TokenValidationRequest() {}

    public TokenValidationRequest(String token) {
        this.token = token;
    }

    // Getters e Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}