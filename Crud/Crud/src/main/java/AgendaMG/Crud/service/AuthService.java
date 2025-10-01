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

    /**
     * Autentica o usuário adaptando-se ao retorno direto de Usuario.
     * @param email O email do usuário.
     * @param senha A senha em texto puro fornecida.
     * @return true se as credenciais forem válidas.
     */
    public boolean autenticar(String email, String senha) {
        // 1. Recebe Usuario (adaptando ao seu Repository)
        Usuario usuarioEncontrado = usuarioRepository.findByEmail(email);

        // 2. Envolve em Optional para tratamento seguro de null
        Optional<Usuario> optionalUsuario = Optional.ofNullable(usuarioEncontrado);

        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            // Compara a senha em texto puro com o hash criptografado
            return passwordEncoder.matches(senha, usuario.getSenha());
        }
        
        return false;
    }

    /**
     * Verifica se um usuário é admin adaptando-se ao retorno direto de Usuario.
     * @param email O email do usuário.
     * @return true se o usuário existir e tiver a role 'ADMIN'.
     */
    public boolean isAdmin(String email) {
        // 1. Recebe Usuario (adaptando ao seu Repository)
        Usuario usuarioEncontrado = usuarioRepository.findByEmail(email);

        // 2. Envolve em Optional para tratamento seguro de null
        Optional<Usuario> optionalUsuario = Optional.ofNullable(usuarioEncontrado);
        
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            
            // A comparação é feita ignorando a caixa (case-insensitive)
            return "ROLE_ADMIN".equalsIgnoreCase(usuario.getRole());
        }
        
        return false;
    }
}