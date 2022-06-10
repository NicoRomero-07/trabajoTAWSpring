package es.trabajotaw.trabajotaw.controller;

import es.trabajotaw.trabajotaw.dao.*;
import es.trabajotaw.trabajotaw.dto.ListaUsuarioDTO;
import es.trabajotaw.trabajotaw.dto.NotificacionDTO;
import es.trabajotaw.trabajotaw.dto.ProductoDTO;
import es.trabajotaw.trabajotaw.dto.UsuarioDTO;
import es.trabajotaw.trabajotaw.entity.ListaUsuario;
import es.trabajotaw.trabajotaw.entity.Notificacion;
import es.trabajotaw.trabajotaw.entity.Producto;
import es.trabajotaw.trabajotaw.entity.Usuario;
import es.trabajotaw.trabajotaw.service.*;
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
    ListaUsuarioService listaUsuarioService;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    TipoUsuarioService tipoUsuarioService;
    @Autowired
    ProductoService productoService;
    @Autowired
    NotificacionService notificacionService;

    @GetMapping("")
    public String inicio(Model model){
        model.addAttribute("listasCompradores",this.listaUsuarioService.listarListas(null));
        return "listasCompradores";
    }
    @PostMapping("")
    public String filtro(Model model, @RequestParam("filtroNombre") String filtroNombre){
        model.addAttribute("listasCompradores",this.listaUsuarioService.listarListas(filtroNombre));
        return "listasCompradores";
    }

    @GetMapping("{id}/edit")
    public String edit(Model model, @PathVariable int id){
        ListaUsuarioDTO listaComprador = this.listaUsuarioService.buscarLista(id);
        List<UsuarioDTO> compradores = this.usuarioService.getCompradores();
        model.addAttribute("listaComprador",listaComprador);
        model.addAttribute("lista",listaComprador);
        model.addAttribute("compradores", compradores);
        return "listaComprador";
    }

    @PostMapping("save")
    public String save(Model model,@ModelAttribute("listaComprador") ListaUsuarioDTO listaComprador){
        if (listaComprador.getUsuarioDTOList().isEmpty() || listaComprador.getUsuarioDTOList().size()==0){
            model.addAttribute("error",true);
            return "listaComprador";
        }else{
            this.listaUsuarioService.guardarLista(listaComprador);
            List<UsuarioDTO> compradores = this.usuarioService.buscarPorTipoUsuario(this.tipoUsuarioService.buscarTipoUsuario(3));
            for (UsuarioDTO comprador : compradores){
                if (comprador.getListaUsuarioDTOList().contains(listaComprador.getIdListaUsuario()) && !listaComprador.getUsuarioDTOList().contains(comprador.getIdUsuario())){
                    List<Integer> lista = comprador.getListaUsuarioDTOList();
                    lista.remove(comprador);
                    comprador.setListaUsuarioDTOList(lista);
                    this.usuarioService.guardarUsuario(comprador);
                }
            }

            for (Integer comprador : listaComprador.getUsuarioDTOList()){
                if (!this.usuarioService.buscarUsuario(comprador).getListaUsuarioDTOList().contains(listaComprador.getIdListaUsuario())) {
                    List<Integer> lista = this.usuarioService.buscarUsuario(comprador).getListaUsuarioDTOList();
                    lista.add(listaComprador.getIdListaUsuario());
                    this.usuarioService.buscarUsuario(comprador).setListaUsuarioDTOList(lista);
                    this.usuarioService.guardarUsuario(this.usuarioService.buscarUsuario(comprador));
                }
            }
            return "redirect:/marketing/";
        }

    }
    @GetMapping("return")
    public String volver(){
        return "redirect:/marketing/";
    }

    @GetMapping("{id}/delete")
    public String delete(@PathVariable("id") Integer id){
        this.listaUsuarioService.borrarLista(id);
        return "redirect:/marketing/";
    }

    @GetMapping("{id}/send")
    public String send(@PathVariable("id") Integer id, HttpSession session){
        List<ProductoDTO> promociones = this.productoService.getProductosEnPromocion();
        StringBuilder contenido = new StringBuilder();
        for (ProductoDTO promocion: promociones){
            contenido.append("Nombre: ").append(promocion.getNombre()).append("<br/>");
            contenido.append("Publicador: ").append(promocion.getPublicador().getNombreUsuario()).append("<br/>");
            contenido.append("Descripcion: ").append(promocion.getDescripcion()).append("<br/>");
            contenido.append("Precio de salida: ").append(promocion.getPrecioSalida()).append("€<br/><br/>");
        }
        NotificacionDTO notificacionCreada = new NotificacionDTO();
        Date now = new Date();
        notificacionCreada.setFechaEnvio(now);
        Usuario notificante = (Usuario)session.getAttribute("usuario");
        notificacionCreada.setNotificante(notificante.getIdUsuario());
        notificacionCreada.setContenido(contenido.toString());
        ListaUsuarioDTO listaUsuario = this.listaUsuarioService.buscarLista(id);
        notificacionCreada.setUsuarioDTOList(new ArrayList<>(listaUsuario.getUsuarioDTOList()));
        notificacionCreada = this.notificacionService.guardarNotificacion(notificacionCreada);

        for(Integer idComprador: listaUsuario.getUsuarioDTOList()){
            UsuarioDTO comprador = this.usuarioService.buscarUsuario(idComprador);
            List<Integer> notificaciones = comprador.getNotificacionDTOList();
            notificaciones.add(notificacionCreada.getIdNotificacion());
            comprador.setNotificacionDTOList(notificaciones);
            this.usuarioService.guardarUsuario(comprador);
        }
        return "redirect:/marketing/";
    }
    @GetMapping("{id}/purcharsers")
    public String purcharsers(Model model, @PathVariable("id") Integer id){
        ListaUsuarioDTO lista = this.listaUsuarioService.buscarLista(id);
        model.addAttribute("lista",lista);
        List<UsuarioDTO> usuarioDTOList = this.usuarioService.getUsuarioDTOListFromId(lista.getUsuarioDTOList());
        model.addAttribute("compradores",usuarioDTOList);
        return "compradores";
    }

    @GetMapping("{listId}/{userId}/messages")
    public String messages (Model model,
                            @PathVariable("listId") Integer listId,
                            @PathVariable("userId") Integer userId){
        UsuarioDTO usuario = this.usuarioService.buscarUsuario(userId);
        List<Integer> idNotificaciones = usuario.getNotificacionDTOList();
        List<NotificacionDTO> notificaciones = this.notificacionService.getNotificacionDTOListFromId(idNotificaciones);
        model.addAttribute("notificaciones",notificaciones);
        model.addAttribute("comprador",usuario);
        model.addAttribute("lista",this.listaUsuarioService.buscarLista(listId));
        return "mensajes";
    }

    @GetMapping("{listId}/{userId}/{notificationId}/deleteMessage")
    public String deleteMessage (@PathVariable("userId") Integer userId,
                                 @PathVariable("notificationId") Integer notificationId){
        UsuarioDTO usuario = this.usuarioService.buscarUsuario(userId);

        NotificacionDTO notificacion = this.notificacionService.buscarNotificacion(notificationId);

        List<Integer> usuarios = notificacion.getUsuarioDTOList();
        usuarios.remove(usuario.getIdUsuario());
        notificacion.setUsuarioDTOList(usuarios);
        this.notificacionService.guardarNotificacion(notificacion);
        List<Integer> notificaciones = usuario.getNotificacionDTOList();
        notificaciones.remove(notificacion.getIdNotificacion());
        usuario.setNotificacionDTOList(notificaciones);
        this.usuarioService.guardarUsuario(usuario);
        if (notificacion.getUsuarioDTOList().isEmpty()){
            this.notificacionService.borrarNotificacion(notificacion.getIdNotificacion());
        }

        return "redirect:/marketing/{listId}/{userId}/messages";
    }
}
