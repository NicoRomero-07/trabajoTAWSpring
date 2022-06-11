package es.trabajotaw.trabajotaw.service;

import es.trabajotaw.trabajotaw.dao.*;
import es.trabajotaw.trabajotaw.dto.*;
import es.trabajotaw.trabajotaw.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository ur;
    private CategoriaRepository cr;
    @Autowired
    private TipoUsuarioRepository tur;
    @Autowired
    private NotificacionRepository notificacionRepository;
    private DireccionRepository dr;
    private DatosEstudioUsuarioRepository deur;
    private EstudioRepository er;
    private CategoriaService cs;
    @Autowired
    private ListaUsuarioRepository lur;

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
            usuarios = this.ur.findAllByNombreUsuarioContaining('%'+filtroNombre+'%');
        }

        return this.listaEntityADTO(usuarios);
    }

    public UsuarioDTO findByNombreUsuario(String nombre) {
        Usuario u = this.ur.findByNombreUsuario(nombre).get(0);
        return u.toDTO();
    }

    /*
    public List<UsuarioDTO> getCompradores(){
        List<Usuario> compradores = this.ur.findByTipoUsuario(this.tur.findByTipo("Comprador"));
        return listaEntityADTO(compradores);
    }
    public UsuarioDTO buscarUsuario (Integer id) {
        Usuario usuario = this.ur.findById(id).orElse(null);
        return usuario.toDTO();
    }

    public List<UsuarioDTO> buscarPorTipoUsuario(TipoUsuarioDTO tipoUsuarioDTO){
        List<Usuario> listaUsuarios =  ur.findByTipoUsuario(new TipoUsuario(tipoUsuarioDTO));
        return this.listaEntityADTO(listaUsuarios);
    }

    public void borrarUsuario (Integer id) {

        Usuario usuario = this.ur.findById(id).orElse(null);

        this.ur.delete(usuario);
    }
    public void guardarUsuario(UsuarioDTO usuarioDTO){
        List<ListaUsuario> listaUsuarioList = null;
        if (usuarioDTO.getListaUsuarioDTOList() != null && !usuarioDTO.getListaUsuarioDTOList().isEmpty()){
            listaUsuarioList = new ArrayList<>();
            for(Integer idUsuario : usuarioDTO.getListaUsuarioDTOList()){
                listaUsuarioList.add(this.lur.findById(idUsuario).orElse(null));
            }
        }

        List<Notificacion> notificacionList = null;
        if (usuarioDTO.getNotificacionDTOList() != null && !usuarioDTO.getNotificacionDTOList().isEmpty()){
            notificacionList = new ArrayList<>();
            for(Integer idNotificacion : usuarioDTO.getNotificacionDTOList()){
                notificacionList.add(this.notificacionRepository.getById(idNotificacion));
            }
        }
        Usuario usuario = new Usuario(usuarioDTO,listaUsuarioList,notificacionList);
        this.ur.save(usuario);
    }



    public List<UsuarioDTO> getUsuarioDTOListFromId(List<Integer> ids){
        List<Usuario> usuarioList = new ArrayList<>();
        for (Integer id : ids){
            usuarioList.add(this.ur.findById(id).orElse(null));
        }
        return this.listaEntityADTO(usuarioList);
    }


    public List<Integer> getIdsFromUsuarioDTOList(List<UsuarioDTO> usuarioList){
        List<Integer> ids = new ArrayList<>();
        for (UsuarioDTO usuario : usuarioList){
            ids.add(usuario.getIdUsuario());
        }
        return ids;
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

    public void modificarUsuario(Usuario usuario){
        this.ur.save(usuario);
    }

    public void modificarUsuario (Integer id,
                                  String nombreUsuario, String contrasenya, String nombre, String primerApellido,
                                  String segundoApellido, String email, Integer direccion, Character sexo,
                                  Integer tipoUsuario, Date fechaNacimiento,String[] categoriasStr) {

        Usuario usuario = this.ur.findById(id).orElse(null);

        this.rellenarUsuario(usuario, nombreUsuario, contrasenya, nombre, primerApellido,
                segundoApellido, email, direccion, sexo, tipoUsuario, fechaNacimiento, categoriasStr);

        this.ur.save(usuario);
    }

    public void modificarUsuario(Integer id, List<Integer> lista){

        Usuario usuario = this.ur.findById(id).orElse(null);

        List<ListaUsuario> listas = new ArrayList<>();
        for(Integer c : lista){
            ListaUsuario l = this.lur.getById(c);
            listas.add(l);
        }
        usuario.setListaUsuarioList(listas);

        this.ur.save(usuario);
    }

    public void modificarNotificacionesUsuario(Integer id, List<Integer> lista){

        Usuario usuario = this.ur.findById(id).orElse(null);

        List<Notificacion> listas = new ArrayList<>();
        for(Integer c : lista){
            //Notificacion l = this.nr.getById(c);
            //listas.add(l);
        }
        usuario.setNotificacionList(listas);

        this.ur.save(usuario);
    }

    public List<CategoriaDTO> categoriasUsuario(Integer id){
        Usuario usuario = this.ur.findById(id).orElse(null);
        List<CategoriaDTO> categoriasDTO = new ArrayList();
        List<Categoria> categorias = usuario.getCategoriaList();

        for(Categoria c : categorias){
            categoriasDTO.add(c.toDTO());
        }
        return categoriasDTO;
    }
    /*
    public List<UsuarioDTO> visualizarEstudio(Integer idEstudio,Integer idEstudioUsuario){
        Estudio estudio = this.er.getById(idEstudio);
        DatosEstudioUsuario estudioUsuario = this.deur.getById(idEstudioUsuario);
        List<Usuario> usuarios = this.ur.visualizarEstudio(estudio,estudioUsuario);
        List<UsuarioDTO> usuariosDTO = this.listaEntityADTO(usuarios);
        return usuariosDTO;
    }

     */

    public List<UsuarioDTO> getAnalistas(){
        List<Usuario> usuarios = this.ur.getAnalistas();
        return this.listaEntityADTO(usuarios);
    }

    public List<UsuarioDTO> getAdministradores(){
        List<Usuario> usuarios = this.ur.getAdministradores();
        return this.listaEntityADTO(usuarios);
    }

    /*
    public List<Double> getIngresosUsuarios(Integer idEstudio,Integer idEstudioUsuario){
        Estudio estudio = this.er.getById(idEstudio);
        DatosEstudioUsuario estudioUsuario = this.deur.getById(idEstudioUsuario);
        List<Double> ingresos = this.ur.getIngresosUsuarios(estudio,estudioUsuario);
        return ingresos;
    }
    */

    public List<ListaUsuarioDTO> listasUsuario(Integer id){
        Usuario usuario = this.ur.findById(id).orElse(null);
        List<ListaUsuarioDTO> listaDTO = new ArrayList();
        List<ListaUsuario> listas = usuario.getListaUsuarioList();

        for(ListaUsuario c : listas){
            listaDTO.add(c.toDTO());
        }
        return listaDTO;
    }

    public List<NotificacionDTO> notificacionesUsuario (Integer id){
        Usuario usuario = this.ur.findById(id).orElse(null);
        List<NotificacionDTO> notificacionDTO = new ArrayList();
        List<Notificacion> notificaciones = usuario.getNotificacionList();

        for(Notificacion c : notificaciones){
            notificacionDTO.add(c.toDTO());
        }
        return notificacionDTO;
    }

    public UsuarioDTO guardarUsuarioAdmin(UsuarioDTO usuario) {

        Usuario usuarioEntity = new Usuario(usuario);

        ur.save(usuarioEntity);
        return usuarioEntity.toDTO();
    }
    /*
    public UsuarioDTO getUsuarioPujaMax(Integer idProducto) {
        return this.ur.getUsuarioPujaMax(idProducto).toDTO();
    }

    public UsuarioDTO comprobarUsuario(String nombreUsuario,String clave){
        Usuario usuario = this.ur.comprobarUsuario(nombreUsuario, clave);
        return usuario == null ? null : usuario.toDTO();
    }
     */
}
