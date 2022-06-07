/**
 *
 * @author Nicolás Zhao(100%)
 */
package es.trabajotaw.trabajotaw.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.trabajotaw.trabajotaw.dao.ListaUsuarioRepository;
import es.trabajotaw.trabajotaw.dao.NotificacionRepository;
import es.trabajotaw.trabajotaw.dao.UsuarioRepository;
import es.trabajotaw.trabajotaw.dto.NotificacionDTO;
import es.trabajotaw.trabajotaw.dto.UsuarioDTO;
import es.trabajotaw.trabajotaw.entity.Notificacion;
import es.trabajotaw.trabajotaw.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NotificacionService {
    @Autowired
    NotificacionRepository notificacionRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    public List<NotificacionDTO> listaEntityADTO(List<Notificacion> lista) {
        List<NotificacionDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Notificacion notificacion:lista) {
                listaDTO.add(notificacion.toDTO());
            }
        }
        return listaDTO;
    }
    public List<NotificacionDTO> listarNotificaciones () {
        return this.listaEntityADTO(this.notificacionRepository.findAll());                
    } 
    
    public NotificacionDTO buscarNotificacion (Integer id) {
        Notificacion notificacion = this.notificacionRepository.findById(id).orElse(null);
        return notificacion.toDTO();
    }
    
    public void borrarNotificacion (Integer id) {
        this.notificacionRepository.deleteById(id);
    }
    
    
    private void rellenarNotificacion (Notificacion notificacion,
                              String contenido, Date fechaEnvio, Integer notificante) {
        
        
        try{
            notificacion.setContenido(contenido);
            SimpleDateFormat formatter = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
            String fechaStr = formatter.format(fechaEnvio);
            Date fecha = formatter.parse(fechaStr);
            notificacion.setFechaEnvio(fecha);
            Usuario n = this.usuarioRepository.findById(notificante).orElse(null);
            notificacion.setNotificante(n);
        }catch(ParseException e){
            
        }
        
    }
    
    public NotificacionDTO crearNotificacion (String contenido, Date fechaEnvio, Integer notificante) {
        Notificacion notificacion = new Notificacion();

        this.rellenarNotificacion(notificacion, contenido, fechaEnvio, notificante);

        this.notificacionRepository.save(notificacion);
        
        return notificacion.toDTO();
    }

    public void modificarNotificacion(Integer id,
                              String contenido, Date fechaEnvio, Integer notificante) {
        
        Notificacion notificacion = this.notificacionRepository.findById(id).orElse(null);

        this.rellenarNotificacion(notificacion, contenido, fechaEnvio, notificante);

        this.notificacionRepository.save(notificacion);
    }
    
    public void modificarNotificacion(Integer id,
                              List<Integer> usuariosId) {
        
        Notificacion notificacion = this.notificacionRepository.findById(id).orElse(null);
        List<Usuario> usuarios = null;
        if (usuariosId != null && !usuariosId.isEmpty()){
            usuarios = new ArrayList();
            for (Integer usuario: usuariosId){
                usuarios.add(this.usuarioRepository.findById(usuario).orElse(null));
            }
            
        }
        notificacion.setUsuarioList(usuarios);
        this.notificacionRepository.save(notificacion);
    }
    
    public List<UsuarioDTO> receptores(Integer id){
        Notificacion notificacion = this.notificacionRepository.findById(id).orElse(null);

        List<UsuarioDTO> usuariosDTO = new ArrayList();
        for (Usuario usuario : notificacion.getUsuarioList()){
            usuariosDTO.add(usuario.toDTO());
        }
        
        return usuariosDTO;
    }
    
    
    
}
