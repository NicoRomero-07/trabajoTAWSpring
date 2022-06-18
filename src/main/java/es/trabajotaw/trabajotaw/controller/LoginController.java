package es.trabajotaw.trabajotaw.controller;

import es.trabajotaw.trabajotaw.dao.UsuarioRepository;
import es.trabajotaw.trabajotaw.dto.CategoriaDTO;
import es.trabajotaw.trabajotaw.dto.DireccionDTO;
import es.trabajotaw.trabajotaw.dto.TipoUsuarioDTO;
import es.trabajotaw.trabajotaw.dto.UsuarioDTO;
import es.trabajotaw.trabajotaw.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @GetMapping("/")
    public String doInit () {
        return "login";
    }

    @PostMapping("/autentica")
    public String doAutentica (HttpSession session ,Model model,
                               @RequestParam("nombreusuario") String usuario, @RequestParam("contrasenya") String clave) {

        Usuario user = this.usuarioRepository.findByNombreUsuarioAndContrasenya(usuario, clave);
        session.setAttribute("usuario",user);
        String id = user.getIdUsuario().toString();
        String goTo;

        if (user == null) {
            String strError = "El usuario o la clave son incorrectos";
            model.addAttribute("error", strError);
            goTo = "login";
        } else {
            if(user.getTipoUsuario().getTipo().equalsIgnoreCase("Administrador")){
                goTo = "redirect:/administrador/vistaAdministrador";
            }else if (user.getTipoUsuario().getTipo().equalsIgnoreCase("Analista")){
                goTo = "redirect:/analista/";
            }else if (user.getTipoUsuario().getTipo().equalsIgnoreCase("Marketing")){
                goTo = "redirect:/marketing/";
            }else if(user.getTipoUsuario().getTipo().equalsIgnoreCase("Comprador")){
                goTo = "redirect:/comprador/vistaComprador/";
            }else if(user.getTipoUsuario().getTipo().equalsIgnoreCase("Vendedor")){
                goTo = "redirect:/vendedor/listaProductos/" + id;
            }else{
                goTo = "login";
            }

        }
        return goTo;
    }
    @GetMapping("/salir")
    public String doExit (HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
