package AgendaMG.Crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller 
public class UsuarioController {

    @GetMapping("/Login")
    public String exibirFormularioLogin() {
        return "Login"; 
    }
}

