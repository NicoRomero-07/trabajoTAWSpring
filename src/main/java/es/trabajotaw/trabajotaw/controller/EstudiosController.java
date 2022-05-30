package es.trabajotaw.trabajotaw.controller;

import es.trabajotaw.trabajotaw.dto.*;
import es.trabajotaw.trabajotaw.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class EstudiosController {

    @Autowired
    private EstudioService estudioService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private DatosEstudioProductoService estudioProductoService;
    @Autowired
    private DatosEstudioUsuarioService estudioUsuarioService;


    @GetMapping("analista")
    public String init(Model model, HttpSession session) {
        List<EstudioDTO> estudios = this.estudioService.listarClientes(null);
        model.addAttribute("estudios", estudios);
        return "estudios";
    }

    @GetMapping("analista/filtro")
    public String filter(Model model, @RequestParam("filtroNombre") String filtroNombre, HttpSession session) {
        List<EstudioDTO> estudios = this.estudioService.listarClientes(filtroNombre);
        model.addAttribute("filtroNombre", filtroNombre);
        model.addAttribute("estudios", estudios);
        return "estudios";
    }

    @GetMapping("analista/delete/{id}")
    public String delete(Model model, @PathVariable String id, HttpSession session){
        EstudioDTO estudio = this.estudioService.getById(Integer.parseInt(id));
        if (estudio.getDatosEstudioProducto() != null) {
            DatosEstudioProductoDTO estudioProducto = this.estudioProductoService.findById(Integer.parseInt(id));
            this.estudioProductoService.delete(estudioProducto.getId());
        } else if (estudio.getDatosEstudioUsuario() != null) {
            DatosEstudioUsuarioDTO estudioUsuario = this.estudioUsuarioService.findById(Integer.parseInt(id));
            this.estudioUsuarioService.delete(estudioUsuario.getId());
        }
        this.estudioService.remove(estudio.getIdEstudio());
        return "redirect:/analista/";
    }

    @GetMapping("analista/copy/{id}")
    public String copy(Model model, @PathVariable String id, HttpSession session){
        List<UsuarioDTO> listaUsuarios = this.usuarioService.listarUsuarios(null);
        model.addAttribute("usuarios", listaUsuarios);
        if (id != null) {
            this.estudioService.copy(id);
        }
        return "redirect:/analista/";
    }

    /*
    @GetMapping("analista/show/{id}")
    public String show(Model model, @PathVariable String id, HttpSession session){
        EstudioDTO estudio = (EstudioDTO) this.estudioService.getById(Integer.parseInt(id));
        model.addAttribute("estudio",estudio);
        DatosEstudioProductoDTO estudioProducto = estudio.getDatosEstudioProducto();
        DatosEstudioUsuarioDTO estudioUsuario = estudio.getDatosEstudioUsuario();
        if(estudioProducto != null){
            List<ProductoDTO> listaProductos = this.productoService.visualizarEstudio(estudioProducto.getId());
            model.addAttribute("listaProductos",listaProductos);
        }else if(estudioUsuario != null){
            List<UsuarioDTO> listaUsuarios = this.usuarioService.visualizarEstudio(estudio.getIdEstudio(),estudioUsuario.getId());
            model.addAttribute("listaUsuarios",listaUsuarios);
            if(estudioUsuario.getIngresos()){
                List<Double> ingresos = this.usuarioService.getIngresosUsuarios(estudio.getIdEstudio(),estudioUsuario.getId());
                model.addAttribute("ingresos",ingresos);
            }
        }
        return "visualizarEstudio";
    }
    */

    @GetMapping("analista/edit/{id}")
    public String edit(Model model, @PathVariable String id, HttpSession session){
        // Cogemos los usuarios analistas y le añadimos los administradores.
        List<UsuarioDTO> listaUsuarios = this.usuarioService.getAnalistas();
        List<UsuarioDTO> listaAdministradores = this.usuarioService.getAdministradores();
        listaUsuarios.addAll(listaAdministradores);
        model.addAttribute("usuarios", listaUsuarios);

        if (id != null) {
            EstudioDTO estudio = this.estudioService.getById(Integer.parseInt(id));
            model.addAttribute("estudio", estudio);
        }
        return "estudio";
    }

    @GetMapping("analista/create")
    public String create(Model model, HttpSession session){
        // Cogemos los usuarios analistas y le añadimos los administradores.
        List<UsuarioDTO> listaUsuarios = this.usuarioService.getAnalistas();
        List<UsuarioDTO> listaAdministradores = this.usuarioService.getAdministradores();
        listaUsuarios.addAll(listaAdministradores);
        model.addAttribute("estudio",new EstudioDTO());
        model.addAttribute("usuarios", listaUsuarios);
        return "estudio";
    }

    @PostMapping("analista/save")
    public String saveEstudio(Model model, HttpSession session,@ModelAttribute("estudio") EstudioDTO estudio,@RequestParam String element){

        EstudioDTO estudioDTO;
        if (estudio.getIdEstudio() == null) {    // Crear nuevo estudio
            estudioDTO = estudioService.save(estudio.getNombre(),
                    estudio.getAnalista().toString(),estudio.getDescripcion(),element,null,null);
        } else {
            estudioDTO = estudioService.save(estudio.getIdEstudio().toString(),estudio.getNombre(),estudio.getAnalista().toString(),estudio.getDescripcion(),element,null,null);
        }
        return "redirect:/analista/estudio/save/datosEstudio/" + estudioDTO.getIdEstudio();
    }

    @GetMapping("analista/estudio/save/datosEstudio/{id}")
    public String datosEstudio(Model model, @PathVariable String id, HttpSession session){

        if (id != null && !id.isEmpty()) {
            EstudioDTO estudio = this.estudioService.getById(Integer.parseInt(id));
            model.addAttribute("estudio", estudio);

            DatosEstudioProductoDTO estudioProducto = null;
            DatosEstudioUsuarioDTO estudioUsuario = null;

            if(estudio.getProducto()){
                estudioProducto = this.estudioProductoService.findById(Integer.parseInt(id));
            }else{
                estudioUsuario = this.estudioUsuarioService.findById(Integer.parseInt(id));
            }
            model.addAttribute("estudioProducto", estudioProducto);
            model.addAttribute("estudioUsuario", estudioUsuario);
        }
        return "datosEstudio";
    }


    @PostMapping("analista/estudio/save/datosEstudio/save")
    public String saveDatosEstudio(Model model,@ModelAttribute("estudioProducto") DatosEstudioProductoDTO estudioProducto,
                                   @ModelAttribute("estudioUsuario") DatosEstudioUsuarioDTO estudioUsuario,@RequestParam("idEstudio") String idEstudio,
                                   HttpSession session) {

        if (estudioProducto.getPrecioActual() != null && estudioProducto.getPrecioSalida() != null
                && estudioProducto.getPrecioActual() < estudioProducto.getPrecioSalida()) {
            return "redirect:/analista/save/datosEstudio/" + idEstudio + "/" + 1;
        } else {
            EstudioDTO estudio = this.estudioService.getById(Integer.parseInt(idEstudio));

            if (estudio.getProducto()) {

                if (estudioProducto.getId() == null && estudio.getProducto()) {    // Crear nuevo estudio
                    estudioProductoService.save(estudioProducto.getCategorias(),estudioProducto.getVendidos(),
                            estudioProducto.getPromocion(),estudioProducto.getPrecioSalida(),
                            estudioProducto.getPrecioActual(), idEstudio);
                }else if(estudio.getProducto()){                                                                // save estudio
                    estudioProductoService.save(estudioProducto.getId().toString(),estudioProducto.getCategorias(),estudioProducto.getVendidos(),
                            estudioProducto.getPromocion(),estudioProducto.getPrecioSalida(),
                            estudioProducto.getPrecioActual(), idEstudio);
                }

                estudioService.save(estudio.getIdEstudio().toString(), null, null, null,
                        null, estudio.getIdEstudio().toString(), null);

            } else {

                if (estudioUsuario.getId() == null && (estudio.getComprador() || estudio.getVendedor())) {    // Crear nuevo estudio
                    estudioUsuarioService.save(estudioUsuario.getNombre(), estudioUsuario.getApellidos(),
                            estudioUsuario.getIngresos(), estudioUsuario.getAscendente(), idEstudio);
                } else if (estudio.getVendedor() || estudio.getComprador()) {   // save estudio
                    estudioUsuarioService.save(estudioUsuario.getId().toString(),estudioUsuario.getNombre(),
                            estudioUsuario.getApellidos(), estudioUsuario.getIngresos(), estudioUsuario.getAscendente(), idEstudio);
                }
                estudioService.save(estudio.getIdEstudio().toString(), null, null, null,
                        null, null, estudio.getIdEstudio().toString());
            }
            return "redirect:/analista/";
        }

    }
}