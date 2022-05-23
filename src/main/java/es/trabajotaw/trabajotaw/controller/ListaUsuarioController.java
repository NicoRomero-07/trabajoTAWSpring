package es.trabajotaw.trabajotaw.controller;

import es.trabajotaw.trabajotaw.dao.ListaUsuarioRepository;
import es.trabajotaw.trabajotaw.entity.Estudio;
import es.trabajotaw.trabajotaw.entity.ListaUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "marketing")
public class ListaUsuarioController {
    @Autowired
    ListaUsuarioRepository listaUsuarioRepository;

    @GetMapping(value= "/")
    public String inicio(Model model, HttpSession session){
        String filtroNombre = "";
        List<ListaUsuario> listas;
        if (filtroNombre == null || filtroNombre.isEmpty()) {
            listas = this.listaUsuarioRepository.findAll();
        } else {
            listas = this.listaUsuarioRepository.findByNombre(filtroNombre);
            model.addAttribute("filtroNombre", filtroNombre);
        }
        model.addAttribute("listasCompradores",listas);
        return "listasCompradores";
    }

}
