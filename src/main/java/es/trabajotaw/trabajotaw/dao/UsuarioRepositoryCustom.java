package es.trabajotaw.trabajotaw.dao;

import es.trabajotaw.trabajotaw.entity.DatosEstudioProducto;
import es.trabajotaw.trabajotaw.entity.DatosEstudioUsuario;
import es.trabajotaw.trabajotaw.entity.Estudio;
import es.trabajotaw.trabajotaw.entity.Usuario;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepositoryCustom{
    List<Usuario> visualizarEstudio(Estudio estudio, DatosEstudioUsuario estudioUsuario);
    List<Double> getIngresosUsuarios(Estudio estudio, DatosEstudioUsuario estudioUsuario);
}
