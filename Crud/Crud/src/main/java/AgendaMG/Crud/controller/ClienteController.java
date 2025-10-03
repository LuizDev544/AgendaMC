package AgendaMG.Crud.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import AgendaMG.Crud.entity.Evento;
import AgendaMG.Crud.service.EventoService;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/usuario")
public class ClienteController {
    @Autowired
    private EventoService eventoService;

    @GetMapping("/public/eventos/tipo/{tipoDoEvento}") 
    public ResponseEntity<List<Evento>> buscarEventoPorTipoDoEvento(@PathVariable String tipoDoEvento ) {
        List<Evento> eventos = eventoService.buscarPorTipo(tipoDoEvento);
        if (eventos == null || eventos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(eventos);
    }
}
