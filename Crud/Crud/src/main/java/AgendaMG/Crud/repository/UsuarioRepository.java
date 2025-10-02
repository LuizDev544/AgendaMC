/* 
package AgendaMG.Crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import AgendaMG.Crud.entity.Admin;

public interface UsuarioRepository extends JpaRepository<Admin, Integer> {
    // 💡 Apenas a classe Usuario é retornada (o que causou o erro de compilação anterior)
    Admin findByEmail(String email);
}
*/