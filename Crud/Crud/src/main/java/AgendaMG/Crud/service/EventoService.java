package AgendaMG.Crud.service;

import AgendaMG.Crud.entity.Evento;
import AgendaMG.Crud.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public List<Evento> listarEventos() {
        return eventoRepository.findAll();
    }

    public Evento salvarEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    public Evento atualizarEvento(int id, Evento eventoAtualizado) {
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
        }).orElseThrow(() -> new RuntimeException("Evento não encontrado"));
    }

    public void deletarEvento(int id) {
        eventoRepository.deleteById(id);
    }
}
