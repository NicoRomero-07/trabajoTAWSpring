package es.trabajotaw.trabajotaw.controller;

import es.trabajotaw.trabajotaw.dao.ListaUsuarioRepository;
import es.trabajotaw.trabajotaw.dao.TipoUsuarioRepository;
import es.trabajotaw.trabajotaw.dao.UsuarioRepository;
import es.trabajotaw.trabajotaw.entity.Estudio;
import es.trabajotaw.trabajotaw.entity.ListaUsuario;
import es.trabajotaw.trabajotaw.entity.TipoUsuario;
import es.trabajotaw.trabajotaw.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ListaUsuarioController {
    @Autowired
    ListaUsuarioRepository listaUsuarioRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    TipoUsuarioRepository tipoUsuarioRepository;

    @GetMapping("marketing")
    public String inicio(Model model, HttpSession session){
        model.addAttribute("listasCompradores",this.listaUsuarioRepository.findAll());
        return "listasCompradores";
    }
    @PostMapping("marketing/filtro")
    public String filtro(Model model, @RequestParam("filtroNombre") String filtroNombre){
        model.addAttribute("listasCompradores",this.listaUsuarioRepository.findByNombreContaining(filtroNombre));
        return "listasCompradores";
    }

    @GetMapping("marketing/edit/{id}")
    public String edit(Model model, @PathVariable int id){
        ListaUsuario listaComprador = this.listaUsuarioRepository.getById(id);
        List<Usuario> compradores = this.usuarioRepository.findByTipoUsuario(this.tipoUsuarioRepository.findByTipo("Comprador"));
        model.addAttribute("listaComprador",listaComprador);
        Map<Usuario,List<ListaUsuario>> relaciones = new HashMap<>();
            for (Usuario usuario: compradores){
                relaciones.put(usuario,usuario.getListaUsuarioList());
            }
            model.addAttribute("relaciones",relaciones);
        return "listaComprador";
    }
    @GetMapping("marketing/nuevo")
    public String nuevo(Model model){
        List<Usuario> compradores = this.usuarioRepository.findByTipoUsuario(this.tipoUsuarioRepository.findByTipo("Comprador"));
        Map<Usuario,List<ListaUsuario>> relaciones = new HashMap<>();
        for (Usuario usuario: compradores){
            relaciones.put(usuario,usuario.getListaUsuarioList());
        }
        model.addAttribute("relaciones",relaciones);
        return "listaComprador";
    }

    @PostMapping("marketing/save")
    public String save(Model model,@RequestParam("id") String id, @RequestParam(value = "compradores",required = false) String[] compradores){

        return "redirect:/marketing";
    }
}
