package es.trabajotaw.trabajotaw.controller;
/*
        Document   : AdminController
        Author     : nicor
*/

import es.trabajotaw.trabajotaw.dto.*;
import es.trabajotaw.trabajotaw.entity.Categoria;
import es.trabajotaw.trabajotaw.entity.Producto;
import es.trabajotaw.trabajotaw.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("administrador")
public class AdminController {

    @Autowired
    private UsuarioService usuariosService;
    @Autowired
    private CategoriaService categoriasService;
    @Autowired
    private ProductoService productosService;
    @Autowired
    private TipoUsuarioService tiposUsuarioService;
    @Autowired
    private DireccionService direccionService;


    @GetMapping(value = "/vistaAdministrador")
    public String inicio(Model model, HttpSession session){
        return "administrador";
    }

    @GetMapping(value = "/administrarUsuarios")
    public String doAdministrarUsuarios(Model model, HttpSession session){
        List<UsuarioDTO> usuarios = usuariosService.listarUsuarios(null);
        model.addAttribute("usuarios", usuarios);
        return "usuarios";
    }
    @PostMapping(value = "/administrarUsuarios")
    public String doPostAdministrarUsuarios(Model model, HttpSession session, @RequestParam("filtroNombre") String filtroNombre){
        if(filtroNombre==null) filtroNombre="";
        List<UsuarioDTO> usuarios = usuariosService.listarUsuarios(filtroNombre);
        model.addAttribute("usuarios", usuarios);
        return "usuarios";
    }
    @GetMapping(value = "/administrarUsuario/{id}")
    public String doAdministrarUsuario(Model model, HttpSession session, @PathVariable("id") Integer id){

        UsuarioDTO usuario = usuariosService.buscarUsuario(id);
        List<TipoUsuarioDTO> tipoUsuarios = tiposUsuarioService.listarTipoUsuarios(null);
        List<CategoriaDTO> categorias = categoriasService.listarCategorias(null);

        model.addAttribute("categorias", categorias);
        model.addAttribute("usuario", usuario);
        model.addAttribute("tipoUsuarios", tipoUsuarios);

        return "usuario";
    }

    @GetMapping(value = "/borrarUsuario/{id}")
    public String doBorrarUsuario(Model model, HttpSession session, @PathVariable("id") Integer id){
        usuariosService.borrarUsuario(id);
        return "redirect:/administrador/administrarUsuarios";
    }


    @PostMapping(value = "/guardarUsuario")
    public String doGuardarUsuario(@ModelAttribute() UsuarioDTO usuario){
        List<Integer> idCategorias = usuario.getCategoriasFavoritas();
        usuario.setCategoriasFavoritasEntity(categoriasService.getCategoriaListFromId(idCategorias));

        UsuarioDTO usuarioDTOID = usuariosService.guardarUsuarioAdmin(usuario);
        direccionService.modificarDireccion(usuario.getDireccion());
        categoriasService.guardarCategorias(usuario.getCategoriasFavoritasEntity(), usuarioDTOID);

        return "redirect:/administrador/administrarUsuarios";
    }
    @GetMapping(value="/nuevoUsuario")
    public String doNuevoUsuario (Model model) {
        UsuarioDTO usuario = new UsuarioDTO();
        DireccionDTO direccionDTO = new DireccionDTO();

        DireccionDTO direccionDTOID = direccionService.guardarDireccion(direccionDTO);
        usuario.setDireccion(direccionDTOID);

        List<TipoUsuarioDTO> tipoUsuarios = tiposUsuarioService.listarTipoUsuarios(null);
        List<CategoriaDTO> categorias = categoriasService.listarCategorias(null);

        model.addAttribute("categorias", categorias);
        model.addAttribute("usuario", usuario);
        model.addAttribute("tipoUsuarios", tipoUsuarios);

        return "usuario";
    }
    @GetMapping(value = "/administrarCategorias")
    public String doAdministrarCategorias(Model model, HttpSession session){
        List<CategoriaDTO> categorias = categoriasService.listarCategorias(null);
        model.addAttribute("categorias", categorias);
        return "categorias";
    }

    @PostMapping(value = "/administrarCategorias")
    public String doPostAdministrarCategorias(Model model, HttpSession session, @RequestParam("filtroNombre") String filtroNombre){
        List<CategoriaDTO> categorias = categoriasService.listarCategorias(filtroNombre);
        model.addAttribute("categorias", categorias);
        return "categorias";
    }

    @GetMapping(value = "/administrarCategoria/{id}")
    public String doAdministrarCategoria(Model model, HttpSession session, @PathVariable("id") Integer id){
        CategoriaDTO categoria = null;
        if(id!=null){
            categoria = categoriasService.buscarCategoria(id);
        }
        model.addAttribute("categoria", categoria);
        return "categoria";
    }

    @GetMapping(value = "/borrarCategoria/{id}")
    public String doBorrarCategoria(Model model, HttpSession session, @PathVariable("id") Integer id){
        categoriasService.borrarCategoria(id);
        return "redirect:/administrador/administrarCategorias";
    }

    @PostMapping(value="/guardarCategoria")
    public String doGuardarCategoria(@ModelAttribute("categoria") CategoriaDTO categoria){
        Categoria categoriaEntidad = new Categoria(categoria);

        categoriasService.modificarCategoria(categoriaEntidad);
        return "redirect:/administrador/administrarCategorias";
    }

    @GetMapping(value="/nuevaCategoria")
    public String doNuevaCategoria (Model model) {
        CategoriaDTO categoria = new CategoriaDTO();
        model.addAttribute("categoria", categoria);
        return "categoria";
    }

    @GetMapping(value = "/administrarProductos")
    public String doAdministrarProductos(Model model, HttpSession session){
        List<ProductoDTO> productos = productosService.listarProductos(null);
        model.addAttribute("productos", productos);
        return "productos";
    }
    @PostMapping(value = "/administrarProductos")
    public String doAdministrarProductos(Model model, HttpSession session,@RequestParam("filtroNombre") String filtroNombre){
        List<ProductoDTO> productos = productosService.listarProductos(filtroNombre);
        model.addAttribute("productos", productos);
        return "productos";
    }
    @GetMapping(value = "/administrarProducto/{id}")
    public String doAdministrarProducto(Model model, HttpSession session, @PathVariable("id") Integer id){
        ProductoDTO producto = productosService.buscarProducto(id);
        List<CategoriaDTO> categorias = categoriasService.listarCategorias(null);
        List<UsuarioDTO>  usuarios = usuariosService.listarUsuarios(null);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("categorias", categorias);
        model.addAttribute("producto", producto);
        return "producto";
    }

    @GetMapping(value = "/nuevoProducto")
    public String doNuevoProducto(Model model, HttpSession session){
        ProductoDTO producto = new ProductoDTO();
        List<CategoriaDTO> categorias = categoriasService.listarCategorias(null);
        List<UsuarioDTO>  usuarios = usuariosService.listarUsuarios(null);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("categorias", categorias);
        model.addAttribute("producto", producto);
        return "producto";
    }

    @GetMapping(value = "/borrarProducto/{id}")
    public String doBorrarProducto(Model model, HttpSession session, @PathVariable("id") Integer id){
        productosService.borrarProducto(id);
        return "redirect:/administrador/administrarProductos";
    }

    @PostMapping(value="/guardarProducto")
    public String doGuardarProducto(@ModelAttribute("producto") ProductoDTO producto){
        UsuarioDTO publicador = usuariosService.findById(producto.getPublicador().getIdUsuario());
        producto.setPublicador(publicador);
        UsuarioDTO comprador = usuariosService.findById(producto.getComprador().getIdUsuario());
        producto.setComprador(comprador);

        Producto productoEntidad = new Producto(producto);
        productosService.modificarProducto(productoEntidad);
        return "redirect:/administrador/administrarProductos";
    }
}