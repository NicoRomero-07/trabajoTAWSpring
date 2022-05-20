package es.trabajotaw.trabajotaw.service;

import es.trabajotaw.trabajotaw.dao.UsuarioRepository;
import es.trabajotaw.trabajotaw.dto.UsuarioDTO;
import es.trabajotaw.trabajotaw.entity.Usuario;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private UsuarioRepository ur;

    public UsuarioDTO comprobarUsuario(String nombreUsuario, String clave){
        Usuario usuario = this.ur.comprobarUsuario(nombreUsuario, clave);
        return usuario == null ? null : usuario.toDTO();
    }
}
