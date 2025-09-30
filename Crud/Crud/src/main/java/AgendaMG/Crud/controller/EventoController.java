package AgendaMG.Crud.controller;

import AgendaMG.Crud.entity.Evento;
import AgendaMG.Crud.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    // Público: listar todos os eventos
    @GetMapping("/public/eventos")
    public List<Evento> listarEventosPublicos() {
        return eventoService.listarEventos();
    }

    // Admin: listar eventos
    @GetMapping("/admin/eventos")
    public List<Evento> listarEventosAdmin() {
        return eventoService.listarEventos();
    }

    // Admin: editar evento
    @PutMapping("/admin/eventos/{id}")
    public Evento atualizarEvento(@PathVariable int id, @RequestBody Evento eventoAtualizado) {
        return eventoService.atualizarEvento(id, eventoAtualizado);
    }

    // Admin: deletar evento
    @DeleteMapping("/admin/eventos/{id}")
    public void deletarEvento(@PathVariable int id) {
        eventoService.deletarEvento(id);
    }
}
