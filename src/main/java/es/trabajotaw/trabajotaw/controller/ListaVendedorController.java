package es.trabajotaw.trabajotaw.controller;

import es.trabajotaw.trabajotaw.dto.CategoriaDTO;
import es.trabajotaw.trabajotaw.dto.ProductoDTO;
import es.trabajotaw.trabajotaw.entity.Categoria;
import es.trabajotaw.trabajotaw.entity.Producto;
import es.trabajotaw.trabajotaw.entity.Usuario;
import es.trabajotaw.trabajotaw.service.CategoriaService;
import es.trabajotaw.trabajotaw.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "vendedor")
public class ListaVendedorController {
    @Autowired
    private ProductoService productoService;
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping(value = "/listaProductos")
    public String doListaProductos(Model model, HttpSession session) {
        Usuario user = (Usuario) session.getAttribute("usuario");
        List<ProductoDTO> productos = this.productoService.listaProductosLogin(user.getIdUsuario());
        model.addAttribute("productos", productos);
        model.addAttribute("publicadorid", user.getIdUsuario());
        return "listaProductos";
    }

    @GetMapping(value = "/publicarProducto")
    public String doNuevoProducto(Model model, HttpSession session) {
        ProductoDTO producto = new ProductoDTO();
        Usuario user = (Usuario) session.getAttribute("usuario");
        producto.setPublicador(user.toDTO());
        model.addAttribute("producto", producto);
        List<CategoriaDTO> categorias = this.categoriaService.listarCategorias("");
        model.addAttribute("categorias", categorias);
        model.addAttribute("isVendedor", 1);
        model.addAttribute("isNew", 1);
        return "publicarProducto";
    }

    @PostMapping(value = "/guardarProducto/{isNew}")
    public String doGuardarProducto(@ModelAttribute("producto") ProductoDTO prod, @PathVariable("isNew") String isNew) {

        Integer nuevo = Integer.parseInt(isNew);
        if(nuevo == 1) {
            this.productoService.crearProducto(prod.getNombre(), prod.getDescripcion(), prod.getPrecioSalida(), prod.getUrlFoto(), prod.getFechaInicioSubasta(), prod.getFechaFinSubasta(), prod.getComprador().getNombre(), prod.getPublicador().getNombre(), prod.getEnPromocion(), prod.getCategoria());
        } else {
            this.productoService.modificarProducto(prod.getIdProducto(), prod.getNombre(), prod.getDescripcion(), prod.getPrecioSalida(), prod.getUrlFoto(), prod.getFechaInicioSubasta(), prod.getFechaFinSubasta(), prod.getComprador().getNombre(), prod.getPublicador().getNombre(), prod.getEnPromocion(), prod.getCategoria());
        }

        return "redirect:/vendedor/listaProductos";
    }
}
