package es.trabajotaw.trabajotaw.controller;

import es.trabajotaw.trabajotaw.dto.ProductoDTO;
import es.trabajotaw.trabajotaw.dto.UsuarioDTO;
import es.trabajotaw.trabajotaw.entity.Usuario;
import es.trabajotaw.trabajotaw.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping(value = "/productosComprados")
    public String doVerProductosComprados(Model model, HttpSession session){
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        List<ProductoDTO> productos = productosService.buscarProductosComprados(usuario.toDTO().getIdUsuario());
        model.addAttribute("productosComprados", productos);
        return "productosComprados";
    }
}
