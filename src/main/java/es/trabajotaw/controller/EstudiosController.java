package es.trabajotaw.controller;

import es.trabajotaw.dto.EstudioDTO;
import es.trabajotaw.dto.UsuarioDTO;
import es.trabajotaw.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpSession;
import java.util.List;

public class EstudiosController {

    @Autowired
    private UsuarioDTO usuarioDTO;

    @RequestMapping(value = "/admin",method = RequestMethod.GET)
    public String inicio(Model model, HttpSession session){
        String filtroNombre = "";
        List<EstudioDTO> estudios;
        if (filtroNombre == null || filtroNombre.isEmpty()) {
            //estudios = this.estudioService.listarClientes(filtroNombre);
        } else {
            //estudios = this.estudioService.listarClientes(filtroNombre);
            model.addAttribute("filtroNombre", filtroNombre);
        }
        return "estudios";
    }
    /*
    String filtroNombre = request.getParameter("filtroNombre");
    List<EstudioDTO> estudios;

            if (filtroNombre == null || filtroNombre.isEmpty()) {
        estudios = this.estudioService.listarClientes(filtroNombre);
    } else {
        estudios = this.estudioService.listarClientes(filtroNombre);
        request.setAttribute("filtroNombre", filtroNombre);
    }

            request.setAttribute("estudios", estudios);
            request.getRequestDispatcher("/WEB-INF/jsp/estudios.jsp").forward(request, response);

     */
}
