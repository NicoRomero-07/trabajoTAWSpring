package es.trabajotaw.trabajotaw.dao;

import es.trabajotaw.trabajotaw.entity.TipoUsuario;
import es.trabajotaw.trabajotaw.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    /*
    @Query("select u from Usuario u where u.nombreUsuario = :usuario and u.contrasenya = :clave")
    Usuario comprobarUsuario(@Param("usuario") String strusuario, @Param("clave") String strclave);
    */
    Usuario findByNombreUsuarioAndContrasenya(String nombreUsuario,String contrasenya);
    List<Usuario> findByTipoUsuario(TipoUsuario tipo);
}
