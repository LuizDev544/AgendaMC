package AgendaMG.Crud.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenValidationResponse {
    private boolean valid;
    private String usuario;
    private String role;

    public TokenValidationResponse() {}

    public TokenValidationResponse(boolean valid, String usuario, String role) {
        this.valid = valid;
        this.usuario = usuario;
        this.role = role;
    }

    public static TokenValidationResponse valid(String usuario, String role) {
        return new TokenValidationResponse(true, usuario, role);
    }

    public static TokenValidationResponse invalid() {
        return new TokenValidationResponse(false, null, null);
    }

}