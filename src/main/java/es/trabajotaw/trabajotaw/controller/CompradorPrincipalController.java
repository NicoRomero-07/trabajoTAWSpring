package es.trabajotaw.trabajotaw.controller;

import es.trabajotaw.trabajotaw.dto.ProductoDTO;
import es.trabajotaw.trabajotaw.dto.UsuarioDTO;
import es.trabajotaw.trabajotaw.entity.Usuario;
import es.trabajotaw.trabajotaw.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("comprador")
public class CompradorPrincipalController {

    @Autowired
    private ProductoService productosService;

    @GetMapping(value = "/vistaComprador")
    public String inicio(Model model, HttpSession session){
        return "comprador";
    }

    @PostMapping(value = "/verProductos")
    public String buscarProductos(Model model, HttpSession session, @RequestParam("buscador") String buscador){
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        List<ProductoDTO> productosBuscados = productosService.listarProductos(buscador);
        List<ProductoDTO> productosFavoritos = productosService.buscarProductosFavoritos(usuario.toDTO().getIdUsuario());
        model.addAttribute("productosBuscados", productosBuscados);
        model.addAttribute("productosFavoritos", productosFavoritos);

        return "listaProductosBuscados";
    }

    @GetMapping(value = "/productosComprados")
    public String doVerProductosComprados(Model model, HttpSession session){
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        List<ProductoDTO> productos = productosService.buscarProductosComprados(usuario.toDTO().getIdUsuario());
        model.addAttribute("productosComprados", productos);
        return "productosComprados";
    }

    @GetMapping(value = "/productosFavoritos")
    public String doVerProductosFavoritos(Model model, HttpSession session){
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        List<ProductoDTO> productos = productosService.buscarProductosFavoritos(usuario.toDTO().getIdUsuario());
        model.addAttribute("productosFavoritos", productos);
        return "productosFavoritos";
    }

    @GetMapping(value = "/notificaciones")
    public String doVerProductosNotificados(Model model, HttpSession session){
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        List<ProductoDTO> favoritos = productosService.buscarProductosFavoritos(usuario.toDTO().getIdUsuario());
        List<ProductoDTO> pujas = productosService.buscarProductosPujados(usuario.toDTO().getIdUsuario());
        model.addAttribute("favoritos", favoritos);
        model.addAttribute("pujas", pujas);
        return "notificaciones";
    }
}
