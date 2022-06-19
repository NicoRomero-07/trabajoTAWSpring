package es.trabajotaw.trabajotaw.controller;

import es.trabajotaw.trabajotaw.dto.ProductoDTO;
import es.trabajotaw.trabajotaw.dto.PujaDTO;
import es.trabajotaw.trabajotaw.dto.UsuarioDTO;
import es.trabajotaw.trabajotaw.entity.Puja;
import es.trabajotaw.trabajotaw.entity.Usuario;
import es.trabajotaw.trabajotaw.service.ListaProductoService;
import es.trabajotaw.trabajotaw.service.ProductoService;
import es.trabajotaw.trabajotaw.service.PujaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("comprador")
public class CompradorPrincipalController {

    @Autowired
    private ProductoService productosService;

    @Autowired
    private PujaService pujaService;

    @Autowired
    private ListaProductoService listaProductoService;

    @GetMapping(value = "/vistaComprador")
    public String inicio(Model model, HttpSession session){
        return "comprador";
    }

    @PostMapping(value = "/verProductos")
    public String buscarProductos(Model model, HttpSession session, @RequestParam("buscador") String buscador){
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        List<ProductoDTO> productosFavoritos = productosService.buscarProductosFavoritos(usuario.getIdUsuario());
        List<ProductoDTO> productosBuscados = productosService.listarProductos(buscador);
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

    @PostMapping(value = "/filtrarProductosComprados")
    public String doFiltrarProductosComprados(Model model, HttpSession session, @RequestParam("buscador") String buscador){
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        List<ProductoDTO> productos = productosService.filtrarProductosComprados(usuario.getIdUsuario(),buscador);
        model.addAttribute("productosFavoritos", productos);

        return "productosComprados";
    }

    @GetMapping(value = "/productosFavoritos")
    public String doVerProductosFavoritos(Model model, HttpSession session){
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        List<ProductoDTO> productos = productosService.buscarProductosFavoritos(usuario.toDTO().getIdUsuario());
        model.addAttribute("productosFavoritos", productos);

        return "productosFavoritos";
    }


    @GetMapping(value = "/anyadirProductosFavoritos/{id}")
    public String anyadirProductosFavoritos(Model model, HttpSession session, @PathVariable("id") Integer productoId){
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        listaProductoService.nuevoProductoFavorito(productoId,usuario.getIdUsuario());

        return "redirect:/comprador/productosFavoritos";
    }

    @GetMapping(value = "/borrarProductosFavoritos/{id}")
    public String borrarProductosFavoritos(Model model, HttpSession session, @PathVariable("id") Integer productoId){
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        listaProductoService.borrarProductoFavorito(productoId,usuario.getIdUsuario());

        return "redirect:/comprador/productosFavoritos";
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

    @GetMapping(value = "/verPuja/{idProducto}")
    public String verPuja(Model model, HttpSession session, @PathVariable("idProducto") Integer idProducto){
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        ProductoDTO producto = productosService.buscarProducto(idProducto);
        List<PujaDTO> pujas = pujaService.buscarPujasProducto(idProducto);
        double precioActual = mayorPuja(pujas);
        model.addAttribute("producto", producto);
        model.addAttribute("precioActual", precioActual);
        model.addAttribute("listaPujas", pujas);
        return "puja";
    }

    @PostMapping(value = "/nuevaPuja/{idProducto}")
    public String nuevaPuja(Model model, HttpSession session, @PathVariable("idProducto") Integer idProducto,
                            @RequestParam("cantidad") double cantidad){

        String redirect = "";
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        ProductoDTO producto = productosService.buscarProducto(idProducto);

        List<PujaDTO> pujas = pujaService.buscarPujasProducto(idProducto);

        if(cantidad > mayorPuja(pujas)){
            PujaDTO puja = new PujaDTO();
            puja.setProducto(producto);
            puja.setComprador(usuario.toDTO());
            puja.setCantidad(cantidad);
            pujaService.guardarPuja(usuario.getIdUsuario(), producto.getIdProducto(),cantidad);
            redirect = "redirect:/comprador/vistaComprador";
        }else{
            model.addAttribute("producto",producto);
            redirect = "pujaError";
        }

        return redirect;
    }

    @PostMapping(value = "/filtrarProductosFavoritos")
    public String filtrarProductosFavoritos(Model model, HttpSession session, @RequestParam("buscador") String buscador){
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        List<ProductoDTO> productosFavoritos = productosService.filtrarProductosFavoritos(usuario.getIdUsuario(), buscador);
        model.addAttribute("productosFavoritos", productosFavoritos);

        return "productosFavoritos";
    }

    @GetMapping(value = "/pujaError")
    public String doVerErrorPuja(Model model, HttpSession session){

        return "pujaError";
    }


    private double mayorPuja(List<PujaDTO> pujas){
        double mayorPuja = 0;
        for(PujaDTO p: pujas){
            double cantidad = p.getCantidad();
            if(cantidad > mayorPuja){
                mayorPuja = cantidad;
            }
        }
        return mayorPuja;
    }
}
