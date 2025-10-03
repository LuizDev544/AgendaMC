package AgendaMG.Crud.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import AgendaMG.Crud.entity.Usuario;
import AgendaMG.Crud.repository.UsuarioRepository; 

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario == null) { 
            throw new UsernameNotFoundException("Usuário não encontrado: " + email);
        }

        return new UsuarioDetails(usuario); 
    }
}