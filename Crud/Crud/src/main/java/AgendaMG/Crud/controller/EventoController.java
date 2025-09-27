package AgendaMG.Crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import AgendaMG.Crud.entity.Evento;
import AgendaMG.Crud.service.EventoService;

import java.util.List;;

@RestController
public class EventoController {
    @Autowired
    EventoService eventoService;

    @GetMapping("/eventos")
    public List<Evento> getAllEventosController(){
        List<Evento> eventos = eventoService.getAllEventosService();
        return eventos;
    }
}