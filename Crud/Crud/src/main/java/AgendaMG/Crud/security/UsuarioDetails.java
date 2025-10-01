package AgendaMG.Crud.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import AgendaMG.Crud.entity.Usuario;

public class UsuarioDetails implements UserDetails {

    private final Usuario usuario;

    public UsuarioDetails(Usuario usuario) {
        this.usuario = usuario;
    }

    // 🚨 ESSENCIAL: Mapeia o campo "ROLE_ADMIN" do seu objeto Usuario
@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    // 🚨 BYPASS: Garante que a role do banco é usada, assumindo que é a STRING CORRETA.
    String roleDoBanco = usuario.getRole(); 

    // Opcional: Se a role por algum motivo vier como "ADMIN", prefixamos aqui.
    if (!roleDoBanco.startsWith("ROLE_")) {
        roleDoBanco = "ROLE_" + roleDoBanco;
    }
    
    return Collections.singletonList(new SimpleGrantedAuthority(roleDoBanco));
}

    @Override
    public String getPassword() {
        return usuario.getSenha();
    }

    @Override
    public String getUsername() {
        return usuario.getEmail();
    }
    
    // Deixe os métodos de status da conta como 'true'
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}