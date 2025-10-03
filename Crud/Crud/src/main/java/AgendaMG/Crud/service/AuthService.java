package AgendaMG.Crud.service;

import java.util.Optional; // Ainda é necessário para segurança interna

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import AgendaMG.Crud.entity.Usuario;
import AgendaMG.Crud.repository.UsuarioRepository;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean autenticar(String email, String senha) {
        Usuario usuarioEncontrado = usuarioRepository.findByEmail(email);
        Optional<Usuario> optionalUsuario = Optional.ofNullable(usuarioEncontrado);

        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            return passwordEncoder.matches(senha, usuario.getSenha());
        }
        
        return false;
    }

    public boolean isAdmin(String email) {
        Usuario usuarioEncontrado = usuarioRepository.findByEmail(email);
        Optional<Usuario> optionalUsuario = Optional.ofNullable(usuarioEncontrado);
        
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            return "ROLE_ADMIN".equalsIgnoreCase(usuario.getRole());
        }
        
        return false;
    }
}