package es.trabajotaw.trabajotaw.controller;

import es.trabajotaw.trabajotaw.dto.UsuarioDTO;
import es.trabajotaw.trabajotaw.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {


    private UsuarioService us;

    public UsuarioService getUs() {
        return us;
    }

    @Autowired
    public void setUs(UsuarioService us) {
        this.us = us;
    }

    @GetMapping("/")
    public String doInit () {
        return "login";
    }

    @PostMapping("/autentica")
    public String doAutentica (Model model,
                               @RequestParam("nombreusuario") String usuario, @RequestParam("contrasenya") String clave, HttpSession session) {

        UsuarioDTO user = this.us.comprobarUsuario(usuario, clave);
        String goTo;

        if (user == null) {
            String strError = "El usuario o la clave son incorrectos";
            model.addAttribute("error", strError);
            goTo = "login";
        } else {
            session.setAttribute("usuario", user);

            if(user.getTipoUsuario().getTipo().equalsIgnoreCase("Administrador")){
                goTo = "redirect:/AdministradorController/";
            }else if (user.getTipoUsuario().getTipo().equalsIgnoreCase("Analista")){
                goTo = "redirect:/EstudiosController/";
            }else if (user.getTipoUsuario().getTipo().equalsIgnoreCase("Marketing")){
                goTo = "redirect:/ListaCompradorController/";
            }else if(user.getTipoUsuario().getTipo().equalsIgnoreCase("Comprador")){
                goTo = "redirect:/CompradorPrincipalController/";
            }else if(user.getTipoUsuario().getTipo().equalsIgnoreCase("Vendedor")){
                goTo = "redirect:/ListaVendedorController/";
            }else{
                goTo = "login";
            }

        }
        return goTo;
    }

}
