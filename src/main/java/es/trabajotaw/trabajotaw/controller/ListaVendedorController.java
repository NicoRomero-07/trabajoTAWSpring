package es.trabajotaw.trabajotaw.controller;

import es.trabajotaw.trabajotaw.dao.ProductoRepository;
import es.trabajotaw.trabajotaw.dto.CategoriaDTO;
import es.trabajotaw.trabajotaw.dto.ProductoDTO;
import es.trabajotaw.trabajotaw.dto.UsuarioDTO;
import es.trabajotaw.trabajotaw.entity.Categoria;
import es.trabajotaw.trabajotaw.entity.Producto;
import es.trabajotaw.trabajotaw.entity.Usuario;
import es.trabajotaw.trabajotaw.service.CategoriaService;
import es.trabajotaw.trabajotaw.service.ProductoService;
import es.trabajotaw.trabajotaw.service.UsuarioService;
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
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping(value = "/listaProductos/{id}")
    public String doListaProductos(Model model, @PathVariable("id") String id) {
        UsuarioDTO user = this.usuarioService.findById(Integer.parseInt(id));
        List<ProductoDTO> productos = this.productoService.listaProductosLogin(user.getIdUsuario());
        model.addAttribute("user", user);
        model.addAttribute("productos", productos);
        model.addAttribute("publicadorid", user.getIdUsuario());
        return "listaProductos";
    }

    @GetMapping(value = "/publicarProducto/{id}")
    public String doNuevoProducto(Model model, @PathVariable("id") String id) {
        ProductoDTO producto = new ProductoDTO();
        UsuarioDTO user = this.usuarioService.findById(Integer.parseInt(id));
        producto.setPublicador(user);
        model.addAttribute("producto", producto);
        List<CategoriaDTO> categorias = this.categoriaService.listarCategorias("");
        model.addAttribute("categorias", categorias);
        model.addAttribute("id", Integer.parseInt(id));
        return "publicarProducto";
    }

    @GetMapping(value = "/editarProducto/{idProducto}/{idUsuario}")
    public String doEditarProducto(Model model, @PathVariable("idProducto") Integer idProducto, @PathVariable("idUsuario") Integer idUsuario) {
        ProductoDTO producto = this.productoService.buscarProducto(idProducto);
        UsuarioDTO user = this.usuarioService.findById(idUsuario);
        model.addAttribute("producto", producto);
        List<CategoriaDTO> categorias = this.categoriaService.listarCategorias("");
        model.addAttribute("categorias", categorias);
        model.addAttribute("id", idUsuario);
        return "publicarProducto";
    }

    @PostMapping(value = "/guardarProducto")
    public String doGuardarProducto(@ModelAttribute("producto") ProductoDTO prod) {

        UsuarioDTO publicador = usuarioService.findByNombreUsuario(prod.getPublicador().getNombreUsuario());
        prod.setComprador(null);
        prod.setPublicador(publicador);
        productoService.modificarProducto(new Producto(prod));

        return "redirect:/vendedor/listaProductos/" + prod.getPublicador().getIdUsuario();
    }

    @GetMapping(value = "/borrarProducto/{idProducto}/{idUsuario}")
    public String doBorrarProducto(@PathVariable("idProducto") Integer idProducto, @PathVariable("idUsuario") String idUsuario) {
        productoService.borrarProducto(idProducto);
        return "redirect:/vendedor/listaProductos/" + idUsuario;
    }

    @GetMapping(value = "terminarPuja/{idProducto}/{idUsuario}")
    public String doTerminarPuja(@PathVariable("idProducto") Integer idProducto, @PathVariable("idUsuario") String idUsuario) {
        ProductoDTO producto = this.productoService.buscarProducto(idProducto);
        producto.setFechaFinSubasta(new Date());
        productoService.modificarProducto(new Producto(producto));
        return "redirect:/vendedor/listaProductos/" + idUsuario;
    }
}
