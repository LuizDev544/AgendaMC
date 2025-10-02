package AgendaMG.Crud.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import AgendaMG.Crud.entity.Admin;
import AgendaMG.Crud.repository.AdminRepository;

@Service
public class AuthService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean autenticar(String email, String senha) {
        Admin adminEncontrado = adminRepository.findByEmail(email);
        
        if (adminEncontrado != null) {
            return passwordEncoder.matches(senha, adminEncontrado.getSenha());
        }
        
        return false;
    }

    // Sempre retorna true pois só existe ADMIN
    public boolean isAdmin(String email) {
        return true;
    }

    public String getRoleFromDatabase(String email) {
        return "ROLE_ADMIN"; // Fixo para ADMIN
    }
}