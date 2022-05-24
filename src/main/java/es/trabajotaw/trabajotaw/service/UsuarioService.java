/**
 *
 * @author Nicolás Zhao(23,5%), Alfonso(23,5%), Nico Alvarez(nicor) (53%)
 */
package es.trabajotaw.trabajotaw.service;

import es.trabajotaw.trabajotaw.dao.*;
import es.trabajotaw.trabajotaw.dto.*;
import es.trabajotaw.trabajotaw.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository ur;
    @Autowired
    private DireccionRepository dr;
    @Autowired
    private CategoriaRepository cr;
    @Autowired
    private TipoUsuarioRepository tur;
    @Autowired
    private EstudioRepository er;
    
/*
    private List<UsuarioDTO> listaEntityADTO (List<Usuario> lista) {
        List<UsuarioDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Usuario usuario:lista) {
                listaDTO.add(usuario.toDTO());
            }
        }
        return listaDTO;
    }
    
    public List<UsuarioDTO> listarUsuarios (String filtroNombre) {
        List<Usuario> usuarios = null;

        if (filtroNombre == null || filtroNombre.isEmpty()) {
            usuarios = this.ur.findAll();        
        } else {
            usuarios = this.ur.findByNombreUsuario(filtroNombre);
        }
        
        return this.listaEntityADTO(usuarios);                
    } 
    
    public List<UsuarioDTO> getCompradores(){
        List<Usuario> compradores = this.ur.getCompradores();
        return listaEntityADTO(compradores);
    }
    
    public UsuarioDTO buscarUsuario (Integer id) {
        Usuario usuario = this.ur.getById(id);
        return usuario.toDTO();
    }
    
    public void borrarUsuario (Integer id) {
        Usuario usuario = this.ur.getById(id);

        this.ur.delete(usuario);
    }
    
    private void rellenarUsuario (Usuario usuario,
                              String nombreUsuario, String contrasenya, String nombre, String primerApellido, 
                              String segundoApellido, String email, Integer direccion, Character sexo, 
                              Integer tipoUsuario, Date fechaNacimiento, String[] categoriasStr) {
        
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setContrasenya(contrasenya);
        usuario.setNombre(nombre);
        usuario.setPrimerApellido(primerApellido);
        usuario.setSegundoApellido(segundoApellido);
        usuario.setEmail(email);
        usuario.setSexo(sexo);
        List<Categoria> categorias = new ArrayList<>();
        if(categoriasStr!=null){
            for(String c : categoriasStr){
                Categoria ca = this.cr.getById(Integer.parseInt(c));
                categorias.add(ca);
            }
        }
            
        usuario.setCategoriaList(categorias);
        
        TipoUsuario tu = this.tur.getById(tipoUsuario);
        usuario.setTipoUsuario(tu);
        
        usuario.setFechaNacimiento(fechaNacimiento);
        Direccion dd = this.dr.getById(direccion);
        
        usuario.setDireccion(dd);
                       
    }
    
    public void crearUsuario (String nombreUsuario, String contrasenya, String nombre, String primerApellido, 
                              String segundoApellido, String email, Integer direccion, Character sexo, 
                              Integer tipoUsuario, Date fechaNacimiento,String[] categoriasStr) {
        Usuario usuario = new Usuario();

        this.rellenarUsuario(usuario, nombreUsuario, contrasenya, nombre, primerApellido, segundoApellido, email, direccion, sexo, tipoUsuario, fechaNacimiento,categoriasStr);

        this.ur.save(usuario);
    }

    public void modificarUsuario (Integer id,
                              String nombreUsuario, String contrasenya, String nombre, String primerApellido, 
                              String segundoApellido, String email, Integer direccion, Character sexo, 
                              Integer tipoUsuario, Date fechaNacimiento,String[] categoriasStr) {
        
        Usuario usuario = this.ur.getById(id);

        this.rellenarUsuario(usuario, nombreUsuario, contrasenya, nombre, primerApellido,
                segundoApellido, email, direccion, sexo, tipoUsuario, fechaNacimiento, categoriasStr);

        this.ur.save(usuario);
    }

    public void modificarUsuario(Integer id, List<Integer> lista){
        
        Usuario usuario = this.ur.getById(id);
        
        List<ListaUsuario> listas = new ArrayList<>();
        for(Integer c : lista){
            ListaUsuario l = this.lf.getById(c);
            listas.add(l);
        }
        usuario.setListaUsuarioList(listas);
        
        this.ur.edit(usuario);
    }
    
    public void modificarNotificacionesUsuario(Integer id, List<Integer> lista){
        
        Usuario usuario = this.ur.getById(id);
        
        List<Notificacion> listas = new ArrayList<>();
        for(Integer c : lista){
            Notificacion l = this.nf.getById(c);
            listas.add(l);
        }
        usuario.setNotificacionList(listas);
        
        this.ur.edit(usuario);
    }
    
    public List<CategoriaDTO> categoriasUsuario(Integer id){
        Usuario usuario = this.ur.getById(id);
        List<CategoriaDTO> categoriasDTO = new ArrayList();
        List<Categoria> categorias = usuario.getCategoriaList();
        
        for(Categoria c : categorias){
            categoriasDTO.add(c.toDTO());
        }
        return categoriasDTO;
    }
    
    public List<UsuarioDTO> visualizarEstudio(Integer idEstudio,Integer idEstudioUsuario){
        Estudio estudio = this.ef.getById(idEstudio);
        DatosEstudioUsuario estudioUsuario = this.deuf.getById(idEstudioUsuario);
        List<Usuario> usuarios = this.ur.visualizarEstudio(estudio,estudioUsuario);
        List<UsuarioDTO> usuariosDTO = this.listaEntityADTO(usuarios);
        return usuariosDTO;
    }

    public List<UsuarioDTO> getAnalistas(){
        List<Usuario> usuarios = this.ur.getAnalistas();
        return this.listaEntityADTO(usuarios);
    }
    
    public List<UsuarioDTO> getAdministradores(){
        List<Usuario> usuarios = this.ur.getAdministradores();
        return this.listaEntityADTO(usuarios);
    }
    
    public List<Double> getIngresosUsuarios(Integer idEstudio,Integer idEstudioUsuario){
        Estudio estudio = this.ef.getById(idEstudio);
        DatosEstudioUsuario estudioUsuario = this.deuf.getById(idEstudioUsuario);
        List<Double> ingresos = this.ur.getIngresosUsuarios(estudio,estudioUsuario);
        return ingresos;
    }
    
    public List<ListaUsuarioDTO> listasUsuario(Integer id){
        Usuario usuario = this.ur.getById(id);
        List<ListaUsuarioDTO> listaDTO = new ArrayList();
        List<ListaUsuario> listas = usuario.getListaUsuarioList();
        
        for(ListaUsuario c : listas){
            listaDTO.add(c.toDTO());
        }
        return listaDTO;
    }
    
    public List<NotificacionDTO> notificacionesUsuario (Integer id){
        Usuario usuario = this.ur.getById(id);
        List<NotificacionDTO> notificacionDTO = new ArrayList();
        List<Notificacion> notificaciones = usuario.getNotificacionList();
        
        for(Notificacion c : notificaciones){
            notificacionDTO.add(c.toDTO());
        }
        return notificacionDTO;
    }
    
    public UsuarioDTO getUsuarioPujaMax(Integer idProducto) {
        return this.ur.getUsuarioPujaMax(idProducto).toDTO();
    }
    
    public UsuarioDTO comprobarUsuario(String nombreUsuario,String clave){
        Usuario usuario = this.ur.comprobarUsuario(nombreUsuario, clave);
        return usuario == null ? null : usuario.toDTO();
    }


     */
}
