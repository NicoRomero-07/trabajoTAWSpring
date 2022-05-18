package es.trabajotaw.trabajotaw.controller;

import es.trabajotaw.trabajotaw.dao.UsuarioRepository;
import es.trabajotaw.trabajotaw.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UsuarioRepository usuariosRepository;

    @RequestMapping(value = "/admin",method = RequestMethod.GET)
    public String inicio(Model model, HttpSession sesion){
        List<Usuario> usuarios = usuariosRepository.findAll();
        model.addAttribute(usuarios);
        return "administrador";
    }
}