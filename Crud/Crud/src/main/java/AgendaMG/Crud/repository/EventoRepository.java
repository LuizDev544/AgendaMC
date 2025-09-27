package AgendaMG.Crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository<Evento> extends JpaRepository<Evento, Integer> {
    //Repositórios (DAO)
}
