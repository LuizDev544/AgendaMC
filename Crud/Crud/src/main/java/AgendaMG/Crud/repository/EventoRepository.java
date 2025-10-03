package AgendaMG.Crud.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import AgendaMG.Crud.entity.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {
    List<Evento> findByTipoDoEvento(String tipoDoEvento);
}
