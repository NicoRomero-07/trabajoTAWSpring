package es.trabajotaw.trabajotaw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("comprador")
public class CompradorPrincipalController {

    @GetMapping(value = "/vistaComprador")
    public String inicio(Model model, HttpSession session){
        return "comprador";
    }
}
