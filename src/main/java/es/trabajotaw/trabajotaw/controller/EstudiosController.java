package es.trabajotaw.trabajotaw.controller;

import es.trabajotaw.trabajotaw.dto.EstudioDTO;
import es.trabajotaw.trabajotaw.dto.UsuarioDTO;
import es.trabajotaw.trabajotaw.service.EstudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpSession;
import java.util.List;

public class EstudiosController {

    @Autowired
    private UsuarioDTO usuarioDTO;
    private EstudioService estudioService;

    @RequestMapping(value = "/EstudiosController",method = RequestMethod.GET)
    public String inicio(Model model, HttpSession session){
        String filtroNombre = "";
        List<EstudioDTO> estudios;
        if (filtroNombre == null || filtroNombre.isEmpty()) {
            estudios = this.estudioService.listarClientes(filtroNombre);
        } else {
            estudios = this.estudioService.listarClientes(filtroNombre);
            model.addAttribute("filtroNombre", filtroNombre);
        }
        model.addAttribute("estudios",estudios);
        return "estudios";
    }
}
