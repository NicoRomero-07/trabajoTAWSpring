package es.trabajotaw.trabajotaw.controller;

import es.trabajotaw.trabajotaw.dao.EstudioRepository;
import es.trabajotaw.trabajotaw.entity.Estudio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "analista")
public class EstudiosController {

    @Autowired
    private EstudioRepository estudioRepository;


    @GetMapping("/")
    public String inicio(Model model, HttpSession session){
        String filtroNombre = "";
        List<Estudio> estudios;
        if (filtroNombre == null || filtroNombre.isEmpty()) {
            estudios = this.estudioRepository.findAll();
        } else {
            estudios = this.estudioRepository.findByNombre(filtroNombre);
            model.addAttribute("filtroNombre", filtroNombre);
        }
        model.addAttribute("estudios",estudios);
        return "estudios";
    }
}
