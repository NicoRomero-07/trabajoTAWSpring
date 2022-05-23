package es.trabajotaw.trabajotaw.controller;

import es.trabajotaw.trabajotaw.dao.ProductoRepository;
import es.trabajotaw.trabajotaw.dto.ProductoDTO;
import es.trabajotaw.trabajotaw.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "vendedor")
public class ListaVendedorController {
    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping(value = "listaProductos")
    public String doListaProductos(Model model, HttpSession session) {
        Usuario user = (Usuario) session.getAttribute("usuario");
        List<ProductoDTO> productos = this.productoRepository.listaProductosPublicadorId(user.getIdUsuario());
        model.addAttribute("productos", productos);
        model.addAttribute("publicadorid", user.getIdUsuario());
        return "listaProductos";
    }
}
