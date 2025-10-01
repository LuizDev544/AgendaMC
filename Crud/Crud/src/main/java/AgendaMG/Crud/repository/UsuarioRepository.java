package AgendaMG.Crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import AgendaMG.Crud.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // 💡 Apenas a classe Usuario é retornada (o que causou o erro de compilação anterior)
    Usuario findByEmail(String email);
}
