package AgendaMG.Crud.service;

import AgendaMG.Crud.entity.Evento;
import AgendaMG.Crud.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public Evento criarEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    public List<Evento> listarEventos() {
        return eventoRepository.findAll();
    }

    public Optional<Evento> buscarPorId(int id) {
        return eventoRepository.findById(id);
    }

    public Optional<Evento> atualizarEvento(int id, Evento eventoAtualizado) {
        return eventoRepository.findById(id).map(evento -> {
            evento.setNomeEvento(eventoAtualizado.getNomeEvento());
            evento.setDescricaoDoEvento(eventoAtualizado.getDescricaoDoEvento());
            evento.setDataDoEvento(eventoAtualizado.getDataDoEvento());
            evento.setLocalDoEvento(eventoAtualizado.getLocalDoEvento());
            evento.setPrecoDoEvento(eventoAtualizado.getPrecoDoEvento());
            evento.setCapacidadeDePessoasNoEvento(eventoAtualizado.getCapacidadeDePessoasNoEvento());
            evento.setTipoDoEvento(eventoAtualizado.getTipoDoEvento());
            evento.setApresentadorDoEvento(eventoAtualizado.getApresentadorDoEvento());
            evento.setDuracaoDoEvento(eventoAtualizado.getDuracaoDoEvento());
            return eventoRepository.save(evento);
        });
    }

    public boolean deletarEvento(int id) {
        return eventoRepository.findById(id).map(evento -> {
            eventoRepository.delete(evento);
            return true;
        }).orElse(false);
    }
}
