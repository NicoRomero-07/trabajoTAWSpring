package es.trabajotaw.trabajotaw.controller;

import es.trabajotaw.trabajotaw.dao.CategoriaRepository;
import es.trabajotaw.trabajotaw.dao.ProductoRepository;
import es.trabajotaw.trabajotaw.dao.TipoUsuarioRepository;
import es.trabajotaw.trabajotaw.dao.UsuarioRepository;
import es.trabajotaw.trabajotaw.entity.Categoria;
import es.trabajotaw.trabajotaw.entity.Producto;
import es.trabajotaw.trabajotaw.entity.TipoUsuario;
import es.trabajotaw.trabajotaw.entity.Usuario;
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
    private UsuarioRepository usuariosRepository;
    @Autowired
    private CategoriaRepository categoriasRepository;
    @Autowired
    private ProductoRepository productosRepository;
    @Autowired
    private TipoUsuarioRepository tiposUsuarioRepository;

    @GetMapping(value = "/vistaAdministrador")
    public String inicio(Model model, HttpSession session){
        return "administrador";
    }

    @GetMapping(value = "/administrarUsuarios")
    public String doAdministrarUsuarios(Model model, HttpSession session){
        List<Usuario> usuarios = usuariosRepository.findAll();
        model.addAttribute("usuarios", usuarios);
        return "usuarios";
    }

    @GetMapping(value = "/administrarUsuario/{id}")
    public String doAdministrarUsuario(Model model, HttpSession session, @PathVariable("id") Integer id){

        Usuario usuario = usuariosRepository.getById(id);
        List<TipoUsuario> tipoUsuarios = tiposUsuarioRepository.findAll();
        List<Categoria> categorias = categoriasRepository.findAll();
        model.addAttribute("categorias", categorias);
        model.addAttribute("usuario", usuario);
        model.addAttribute("tipoUsuarios", tipoUsuarios);

        return "usuario";
    }

    @GetMapping(value = "/administrarCategorias")
    public String doAdministrarCategorias(Model model, HttpSession session){
        List<Categoria> categorias = categoriasRepository.findAll();
        model.addAttribute("categorias", categorias);
        return "categorias";
    }

    @GetMapping(value = "/administrarCategoria/{id}")
    public String doAdministrarCategoria(Model model, HttpSession session, @PathVariable("id") Integer id){
        Categoria categoria = categoriasRepository.getById(id);
        model.addAttribute("categoria", categoria);
        return "categoria";
    }

    @GetMapping(value = "/administrarProductos")
    public String doAdministrarProductos(Model model, HttpSession session){
        List<Producto> productos = productosRepository.findAll();
        model.addAttribute("productos", productos);
        return "productos";
    }

    @GetMapping(value = "/administrarProducto/{id}")
    public String doAdministrarProducto(Model model, HttpSession session, @PathVariable("id") Integer id){
        Producto producto = productosRepository.getById(id);
        List<Categoria> categorias = categoriasRepository.findAll();
        model.addAttribute("categorias", categorias);
        model.addAttribute("producto", producto);
        return "producto";
    }
}
