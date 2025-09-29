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

    @GetMapping("/public/eventos")
    public List<Evento> listarEventosPublicos() {
        return eventoService.listarEventos();
    }

    @GetMapping("/admin/eventos")
    public List<Evento> listarEventosAdmin() {
        return eventoService.listarEventos();
    }

    @PostMapping("/admin/eventos")
    public Evento criarEvento(@RequestBody Evento evento) {
        return eventoService.salvarEvento(evento);
    }

    @PutMapping("/admin/eventos/{id}")
    public Evento atualizarEvento(@PathVariable int id, @RequestBody Evento eventoAtualizado) {
        return eventoService.atualizarEvento(id, eventoAtualizado);
    }

    @DeleteMapping("/admin/eventos/{id}")
    public void deletarEvento(@PathVariable int id) {
        eventoService.deletarEvento(id);
    }
}
