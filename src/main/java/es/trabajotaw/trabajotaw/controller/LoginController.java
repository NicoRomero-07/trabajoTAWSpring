package es.trabajotaw.trabajotaw.controller;

import es.trabajotaw.trabajotaw.dao.UsuarioRepository;
import es.trabajotaw.trabajotaw.dto.CategoriaDTO;
import es.trabajotaw.trabajotaw.dto.DireccionDTO;
import es.trabajotaw.trabajotaw.dto.TipoUsuarioDTO;
import es.trabajotaw.trabajotaw.dto.UsuarioDTO;
import es.trabajotaw.trabajotaw.entity.Usuario;
import es.trabajotaw.trabajotaw.service.CategoriaService;
import es.trabajotaw.trabajotaw.service.DireccionService;
import es.trabajotaw.trabajotaw.service.TipoUsuarioService;
import es.trabajotaw.trabajotaw.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private DireccionService direccionService;
    @Autowired
    private TipoUsuarioService tipoUsuarioService;
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private UsuarioService usuarioService;

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

    @GetMapping(value="/registro")
    public String doNuevoUsuario (Model model) {
        UsuarioDTO usuario = new UsuarioDTO();
        DireccionDTO direccionDTO = new DireccionDTO();

        DireccionDTO direccionDTOID = direccionService.guardarDireccion(direccionDTO);
        usuario.setDireccion(direccionDTOID);

        List<TipoUsuarioDTO> tipoUsuarios = tipoUsuarioService.listarTipoUsuarios(null);
        List<CategoriaDTO> categorias = categoriaService.listarCategorias(null);

        model.addAttribute("categorias", categorias);
        model.addAttribute("usuario", usuario);
        model.addAttribute("tipoUsuarios", tipoUsuarios);

        return "registro";
    }

    @PostMapping(value = "/guardarRegistro")
    public String doGuardarUsuario(@ModelAttribute() UsuarioDTO usuario){
        List<Integer> idCategorias = usuario.getCategoriasFavoritas();
        usuario.setCategoriasFavoritasEntity(categoriaService.getCategoriaListFromId(idCategorias));

        UsuarioDTO usuarioDTOID = usuarioService.guardarUsuarioAdmin(usuario);
        direccionService.modificarDireccion(usuario.getDireccion());
        if(idCategorias.size()>0){
            categoriaService.guardarCategorias(usuario.getCategoriasFavoritasEntity(), usuarioDTOID);
        }
        return "redirect:/";
    }
}
