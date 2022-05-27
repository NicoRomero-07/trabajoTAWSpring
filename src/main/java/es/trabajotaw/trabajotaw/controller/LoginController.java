package es.trabajotaw.trabajotaw.controller;

import es.trabajotaw.trabajotaw.dao.UsuarioRepository;
import es.trabajotaw.trabajotaw.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public String doInit () {
        return "login";
    }

    @PostMapping("/autentica")
    public String doAutentica (Model model,
                               @RequestParam("nombreusuario") String usuario, @RequestParam("contrasenya") String clave, HttpSession session) {

        Usuario user = this.usuarioRepository.findByNombreUsuarioAndContrasenya(usuario, clave);
        String goTo;

        if (user == null) {
            String strError = "El usuario o la clave son incorrectos";
            model.addAttribute("error", strError);
            goTo = "login";
        } else {
            session.setAttribute("usuario", user);

            if(user.getTipoUsuario().getTipo().equalsIgnoreCase("Administrador")){
                goTo = "redirect:/administrador/vistaAdministrador";
            }else if (user.getTipoUsuario().getTipo().equalsIgnoreCase("Analista")){
                goTo = "redirect:/analista/";
            }else if (user.getTipoUsuario().getTipo().equalsIgnoreCase("Marketing")){
                goTo = "redirect:/marketing/";
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
