package es.trabajotaw.trabajotaw.controller;

import es.trabajotaw.trabajotaw.dao.*;
import es.trabajotaw.trabajotaw.entity.ListaUsuario;
import es.trabajotaw.trabajotaw.entity.Notificacion;
import es.trabajotaw.trabajotaw.entity.Producto;
import es.trabajotaw.trabajotaw.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("marketing")
public class MarketingController {
    @Autowired
    ListaUsuarioRepository listaUsuarioRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    TipoUsuarioRepository tipoUsuarioRepository;
    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    NotificacionRepository notificacionRepository;

    @GetMapping("/")
    public String inicio(Model model, HttpSession session){
        model.addAttribute("listasCompradores",this.listaUsuarioRepository.findAll());
        return "listasCompradores";
    }
    @PostMapping("/filtro")
    public String filtro(Model model, @RequestParam("filtroNombre") String filtroNombre){
        model.addAttribute("listasCompradores",this.listaUsuarioRepository.findByNombreContaining(filtroNombre));
        return "listasCompradores";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable int id){
        ListaUsuario listaComprador = this.listaUsuarioRepository.findById(id).orElse(new ListaUsuario());
        List<Usuario> compradores = this.usuarioRepository.findByTipoUsuario(this.tipoUsuarioRepository.findByTipo("Comprador"));
        model.addAttribute("listaComprador",listaComprador);
        model.addAttribute("lista",listaComprador);
        model.addAttribute("compradores", compradores);
        return "listaComprador";
    }

    @PostMapping("/save")
    public String save(Model model,@ModelAttribute("listaComprador") ListaUsuario listaComprador){
        if (listaComprador.getUsuarioList().isEmpty() || listaComprador.getUsuarioList().size()==0){
            model.addAttribute("error",true);
            return "listaComprador";
        }else{
            this.listaUsuarioRepository.save(listaComprador);
            List<Usuario> compradores = this.usuarioRepository.findByTipoUsuario(this.tipoUsuarioRepository.findByTipo("Comprador"));
            for (Usuario comprador : compradores){
                if (comprador.getListaUsuarioList().contains(compradores) && !listaComprador.getUsuarioList().contains(comprador)){
                    List<ListaUsuario> lista = comprador.getListaUsuarioList();
                    lista.remove(comprador);
                    comprador.setListaUsuarioList(lista);
                    this.usuarioRepository.save(comprador);
                }
            }

            for (Usuario comprador : listaComprador.getUsuarioList()){
                if (!comprador.getListaUsuarioList().contains(listaComprador)) {
                    List<ListaUsuario> lista = comprador.getListaUsuarioList();
                    lista.add(listaComprador);
                    comprador.setListaUsuarioList(lista);
                    this.usuarioRepository.save(comprador);
                }
            }
            return "redirect:/marketing/";
        }

    }
    @GetMapping("/return")
    public String volver(){
        return "redirect:/marketing/";
    }

    @GetMapping("{id}/delete")
    public String delete(@PathVariable("id") Integer id){
        this.listaUsuarioRepository.deleteById(id);
        return "redirect:/marketing/";
    }

    @GetMapping("{id}/send")
    public String send(@PathVariable("id") Integer id, HttpSession session){
        List<Producto> promociones = this.productoRepository.findByEnPromocion(true);
        StringBuilder contenido = new StringBuilder();
        for (Producto promocion: promociones){
            contenido.append("Nombre: ").append(promocion.getNombre()).append("<br/>");
            contenido.append("Publicador: ").append(promocion.getPublicador().getNombreUsuario()).append("<br/>");
            contenido.append("Descripcion: ").append(promocion.getDescripcion()).append("<br/>");
            contenido.append("Precio de salida: ").append(promocion.getPrecioSalida()).append("€<br/><br/>");
        }
        Notificacion notificacionCreada = new Notificacion();
        Date now = new Date();
        notificacionCreada.setFechaEnvio(now);
        Usuario notificante = (Usuario)session.getAttribute("usuario");
        notificacionCreada.setNotificante(notificante);
        notificacionCreada.setContenido(contenido.toString());
        ListaUsuario listaUsuario = this.listaUsuarioRepository.findById(id).orElse(null);
        notificacionCreada.setUsuarioList(new ArrayList<>(listaUsuario.getUsuarioList()));
        this.notificacionRepository.save(notificacionCreada);

        for(Usuario comprador: listaUsuario.getUsuarioList()){
            List<Notificacion> notificaciones = comprador.getNotificacionList();
            notificaciones.add(notificacionCreada);
            this.usuarioRepository.save(comprador);
        }
        return "redirect:/marketing/";
    }

    @GetMapping("{id}/purcharsers")
    public String purcharsers(Model model, @PathVariable("id") Integer id){
        ListaUsuario lista = this.listaUsuarioRepository.findById(id).orElse(null);
        model.addAttribute("lista",lista);
        model.addAttribute("compradores",lista.getUsuarioList());
        return "compradores";
    }
    @GetMapping("{listId}/{userId}/messages")
    public String messages (Model model,
                            @PathVariable("listId") Integer listId,
                            @PathVariable("userId") Integer userId){
        Usuario usuario = this.usuarioRepository.findById(userId).orElse(null);
        List<Notificacion> notificaciones = usuario.getNotificacionList();
        model.addAttribute("notificaciones",notificaciones);
        model.addAttribute("comprador",usuario);
        model.addAttribute("lista",this.listaUsuarioRepository.findById(listId).orElse(null));
        return "mensajes";
    }

    @GetMapping("{listId}/{userId}/{notificationId}/deleteMessage")
    public String deleteMessage (Model model,
                                 @PathVariable("userId") Integer userId,
                                 @PathVariable("notificationId") Integer notificationId){
        Usuario usuario = this.usuarioRepository.findById(userId).orElse(null);

        Notificacion notificacion = this.notificacionRepository.findById(notificationId).orElse(null);

        List<Usuario> usuarios = notificacion.getUsuarioList();
        usuarios.remove(usuario);
        notificacion.setUsuarioList(usuarios);
        this.notificacionRepository.save(notificacion);
        List<Notificacion> notificaciones = usuario.getNotificacionList();
        notificaciones.remove(notificacion);
        usuario.setNotificacionList(notificaciones);
        this.usuarioRepository.save(usuario);
        if (notificacion.getUsuarioList().isEmpty()){
            this.notificacionRepository.delete(notificacion);
        }

        return "redirect:/marketing/{listId}/{userId}/messages";
    }
}
