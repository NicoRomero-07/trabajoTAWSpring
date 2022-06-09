package es.trabajotaw.trabajotaw.controller;

import es.trabajotaw.trabajotaw.dao.CategoriaRepository;
import es.trabajotaw.trabajotaw.dao.ProductoRepository;
import es.trabajotaw.trabajotaw.dao.TipoUsuarioRepository;
import es.trabajotaw.trabajotaw.dao.UsuarioRepository;
import es.trabajotaw.trabajotaw.dto.CategoriaDTO;
import es.trabajotaw.trabajotaw.dto.ProductoDTO;
import es.trabajotaw.trabajotaw.dto.TipoUsuarioDTO;
import es.trabajotaw.trabajotaw.dto.UsuarioDTO;
import es.trabajotaw.trabajotaw.entity.Categoria;
import es.trabajotaw.trabajotaw.entity.Producto;
import es.trabajotaw.trabajotaw.entity.TipoUsuario;
import es.trabajotaw.trabajotaw.entity.Usuario;
import es.trabajotaw.trabajotaw.service.CategoriaService;
import es.trabajotaw.trabajotaw.service.ProductoService;
import es.trabajotaw.trabajotaw.service.TipoUsuarioService;
import es.trabajotaw.trabajotaw.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
        Usuario usuarioEntidad = new Usuario(usuario);
        usuariosService.modificarUsuario(usuarioEntidad);
        return "redirect:/administrador/administrarUsuarios";
    }

    @GetMapping(value = "/administrarCategorias")
    public String doAdministrarCategorias(Model model, HttpSession session){
        List<CategoriaDTO> categorias = categoriasService.listarCategorias(null);
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

    @GetMapping(value = "/administrarProductos")
    public String doAdministrarProductos(Model model, HttpSession session){
        List<ProductoDTO> productos = productosService.listarProductos(null);
        model.addAttribute("productos", productos);
        return "productos";
    }

    @GetMapping(value = "/administrarProducto/{id}")
    public String doAdministrarProducto(Model model, HttpSession session, @PathVariable("id") Integer id){
        ProductoDTO producto = productosService.buscarProducto(id);
        List<CategoriaDTO> categorias = categoriasService.listarCategorias(null);
        model.addAttribute("categorias", categorias);
        model.addAttribute("producto", producto);
        return "producto";
    }

    @GetMapping(value = "/borrarProducto/{id}")
    public String doBorrarProducto(Model model, HttpSession session, @PathVariable("id") Integer id){
        productosService.borrarProducto(id);
        return "redirect:/administrador/administrarProductos";
    }
}