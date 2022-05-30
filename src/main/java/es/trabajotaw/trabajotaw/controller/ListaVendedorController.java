package es.trabajotaw.trabajotaw.controller;

import es.trabajotaw.trabajotaw.dto.CategoriaDTO;
import es.trabajotaw.trabajotaw.dto.ProductoDTO;
import es.trabajotaw.trabajotaw.entity.Categoria;
import es.trabajotaw.trabajotaw.entity.Usuario;
import es.trabajotaw.trabajotaw.service.CategoriaService;
import es.trabajotaw.trabajotaw.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("producto", null);
        List<CategoriaDTO> categorias = this.categoriaService.listarCategorias("");
        model.addAttribute("categorias", categorias);

        return "publicarProducto";
    }

    @GetMapping(value = "/guardarProducto")
    public String doGuardarProducto(Model model, HttpSession session) {
        String nombreproducto = (String) model.getAttribute("nombreproducto");
        String descripcion = (String) model.getAttribute("descripcion");
        Integer preciosalida = (Integer) model.getAttribute("preciosalida");
        String urlimagen = (String) model.getAttribute("imagen");
        Date fechainicio = (Date) model.getAttribute("fechaInicio");
        Date fechafin = (Date) model.getAttribute("fechaFin");
        String comprador = "";
        Boolean promocion = (Boolean) model.getAttribute("promocion");
        Usuario publicador = (Usuario) session.getAttribute("usuario");
        Integer categoria = (Integer) session.getAttribute("categoria");

        this.productoService.crearProducto(nombreproducto, descripcion, preciosalida.doubleValue(), urlimagen,
                fechainicio, fechafin, comprador, publicador.getNombreUsuario(), promocion, categoria);

        return "guardarProducto";
    }
}
