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
    private ProductoService productoService;
    @Autowired
    private DatosEstudioProductoService estudioProductoService;
    @Autowired
    private DatosEstudioUsuarioService estudioUsuarioService;

    @GetMapping("analista")
    public String inicio(Model model, HttpSession session) {
        List<EstudioDTO> estudios = this.estudioService.listarClientes(null);
        model.addAttribute("estudios", estudios);
        return "estudios";
    }

    @GetMapping("analista/filtro")
    public String filtro(Model model, @RequestParam("filtroNombre") String filtroNombre, HttpSession session) {
        List<EstudioDTO> estudios = this.estudioService.listarClientes(filtroNombre);
        model.addAttribute("filtroNombre", filtroNombre);
        model.addAttribute("estudios", estudios);
        return "estudios";
    }

    @GetMapping("analista/delete/{id}")
    public String delete(Model model, @PathVariable String id, HttpSession session){
        EstudioDTO estudio = this.estudioService.getById(Integer.parseInt(id));
        if (estudio.getDatosEstudioProducto() != null) {
            DatosEstudioProductoDTO estudioProducto = this.estudioProductoService.getById(Integer.parseInt(id));
            this.estudioProductoService.delete(estudioProducto.getId());
        } else if (estudio.getDatosEstudioUsuario() != null) {
            DatosEstudioUsuarioDTO estudioUsuario = this.estudioUsuarioService.getById(Integer.parseInt(id));
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
    public String save(Model model, @PathVariable String id, HttpSession session){
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

    @PostMapping("analista/save/{id}")
    public String saveEstudio(Model model, HttpSession session,
                              @RequestParam String id,@RequestParam String nombre,
                              @RequestParam String analista,@RequestParam String descripcion,
                              @RequestParam String element){

        if (id == null || id.isEmpty()) {    // Crear nuevo estudio
            EstudioDTO estudioDTO = estudioService.save(nombre,analista,descripcion,element,null,null);
            id = estudioDTO.getIdEstudio().toString();
        } else {                                   // savear estudio
            estudioService.save(id,nombre,analista,descripcion,element,null,null);
        }
        return "redirect:/analista/save/datosEstudio/" + id;
    }

    @GetMapping("analista/edit/datosEstudio/{id}")
    public String datosEstudio(Model model, @PathVariable String id, HttpSession session){
        
        if (id != null && !id.isEmpty()) {
            EstudioDTO estudio = this.estudioService.getById(Integer.parseInt(id));
            model.addAttribute("estudio", estudio);

            DatosEstudioProductoDTO estudioProducto = null;
            DatosEstudioUsuarioDTO estudioUsuario = null;
            
            if(estudio.getProducto()){
                estudioProducto = this.estudioProductoService.getById(Integer.parseInt(id));
            }else{
                estudioUsuario = this.estudioUsuarioService.getById(Integer.parseInt(id));
            }
            if (estudioProducto != null) {
                model.addAttribute("estudioProducto", estudioProducto);
            } else if (estudioUsuario != null) {
                model.addAttribute("estudioUsuario", estudioUsuario);
            }
        }
        return "datosEstudio";
    }

    @PostMapping("analista/save/datosEstudio/{id}")
    public String saveDatosEstudio(Model model,
                                   @RequestParam String idEstudio,@RequestParam String idEstudioProducto,
                                   @RequestParam String[] elementsProducto,@RequestParam String sprecioSalida,
                                   @RequestParam String sprecioActual,@RequestParam String idEstudioUsuario,
                                   @RequestParam String[] elementsUsuario,
                                   HttpSession session) {
        EstudioDTO estudio;
        DatosEstudioProductoDTO estudioProducto;
        DatosEstudioUsuarioDTO estudioUsuario;

        Double precioSalida = sprecioSalida != null && !sprecioSalida.isEmpty() ? Double.parseDouble(sprecioSalida) : null;
        Double precioActual = sprecioActual != null && !sprecioActual.isEmpty() ? Double.parseDouble(sprecioActual) : null;

        if (precioSalida != null && precioActual != null && precioActual < precioSalida) {
            return "redirect:/analista/save/datosEstudio/" + idEstudio + "/" + 1;
        } else {
            estudio = this.estudioService.getById(Integer.parseInt(idEstudio));

            if (estudio.getProducto()) {
                Boolean categorias = Boolean.FALSE;
                Boolean vendidos = Boolean.FALSE;
                Boolean promocion = Boolean.FALSE;

                if (elementsProducto != null) {
                    for (String s : elementsProducto) {
                        if (s.equals("categorias")) {
                            categorias = Boolean.TRUE;
                        } else if (s.equals("vendidos")) {
                            vendidos = Boolean.TRUE;
                        } else if (s.equals("enPromocion")) {
                            promocion = Boolean.TRUE;
                        }
                    }
                }

                if ((idEstudioProducto == null || idEstudioProducto.isEmpty()) && estudio.getProducto()) {    // Crear nuevo estudio
                    estudioProductoService.save(categorias, vendidos,
                            promocion, precioSalida,
                            precioActual, idEstudio);
                } else if (estudio.getProducto()) {                                                                // savear estudio
                    estudioProductoService.save(idEstudioProducto, categorias,
                            vendidos, promocion, precioSalida,
                            precioActual, idEstudio);
                }

                estudioService.save(estudio.getIdEstudio().toString(), null, null, null, null, idEstudioProducto, null);

            } else {
                Boolean nombre = Boolean.FALSE;
                Boolean apellidos = Boolean.FALSE;
                Boolean ingresos = Boolean.FALSE;
                Boolean ascendente = Boolean.FALSE;

                if (elementsUsuario != null) {
                    for (String s : elementsUsuario) {
                        if (s.equals("nombre")) {
                            nombre = Boolean.TRUE;
                        } else if (s.equals("apellidos")) {
                            apellidos = Boolean.TRUE;
                        } else if (s.equals("ingresos") || s.equals("gastos")) {
                            ingresos = Boolean.TRUE;
                        } else if (s.equals("ascendente")) {
                            ascendente = Boolean.TRUE;
                        }
                    }
                }

                if ((idEstudioUsuario == null || idEstudioUsuario.isEmpty()) && (estudio.getVendedor() || estudio.getComprador())) {    // Crear nuevo estudio
                    estudioUsuarioService.save(nombre, apellidos, ingresos, ascendente, idEstudio);
                } else if (estudio.getVendedor() || estudio.getComprador()) {                                                                // savear estudio
                    estudioUsuarioService.save(idEstudioUsuario, nombre, apellidos, ingresos, ascendente, idEstudio);
                }
                estudioService.save(estudio.getIdEstudio().toString(), null, null, null, null, null, idEstudioUsuario);
            }
            return "redirect:/analista/";
        }

    }
}
