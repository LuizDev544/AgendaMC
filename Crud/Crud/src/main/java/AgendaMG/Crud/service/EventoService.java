package AgendaMG.Crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AgendaMG.Crud.entity.Evento;
import AgendaMG.Crud.repository.EventoRepository;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public List<Evento> getAllEventosService() {
        return eventoRepository.findAll();
    }
}