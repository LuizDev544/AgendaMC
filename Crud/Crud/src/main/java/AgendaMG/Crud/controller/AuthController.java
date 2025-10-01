package AgendaMG.Crud.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import AgendaMG.Crud.entity.Usuario;
import AgendaMG.Crud.service.AuthService;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario loginRequest, HttpSession session) {
        if (authService.autenticar(loginRequest.getEmail(), loginRequest.getSenha())) {
            session.setAttribute("usuario", loginRequest.getEmail());

            String role;
            if (authService.isAdmin(loginRequest.getEmail())) {
                role = "ADMIN";
            } else {
                role = "USER";
            }
            session.setAttribute("role", role);

            return ResponseEntity.ok(Map.of(
                    "message", "Login realizado com sucesso",
                    "role", role,
                    "usuario", loginRequest.getEmail()
            ));
        }

        return ResponseEntity.status(401).body(Map.of(
                "message", "Credenciais inválidas!"
        ));
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok(Map.of(
                "message", "Logout realizado!"
        ));
    }

    @GetMapping("/me")
    public ResponseEntity<?> usuarioLogado(HttpSession session) {
        String usuario = (String) session.getAttribute("usuario");
        String role = (String) session.getAttribute("role");

        if (usuario != null) {
            return ResponseEntity.ok(Map.of(
                    "usuario", usuario,
                    "role", role
            ));
        }

        return ResponseEntity.status(401).body(Map.of(
                "message", "Nenhum usuário logado."
        ));
    }
}
