package es.trabajotaw.trabajotaw.dao;

import es.trabajotaw.trabajotaw.dto.UsuarioDTO;
import es.trabajotaw.trabajotaw.entity.TipoUsuario;
import es.trabajotaw.trabajotaw.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("select u from Usuario u where upper(u.nombreUsuario) like upper(:usuario) and upper(u.contrasenya) like upper(:clave)")
    Usuario comprobarUsuario(@Param("usuario") String nombreUsuario, @Param("clave") String clave);

    Usuario findByNombreUsuarioAndContrasenya(String nombreUsuario,String contrasenya);

    @Query("select u from Usuario u where upper(u.tipoUsuario.tipo) like upper('analista')")
    List<Usuario> getAnalistas();

    @Query("select u from Usuario u where upper(u.tipoUsuario.tipo) like upper('administrador')")
    List<Usuario> getAdministradores();

    List<Usuario> findByNombreUsuario(String comprador);

    @Query("select u from Usuario u where upper(u.nombreUsuario) like upper(:nombreUsuario)")
    List<Usuario> findAllByNombreUsuarioContaining(@Param("nombreUsuario") String nombreUsuario);

    List<Usuario> findByTipoUsuario(TipoUsuario tipo);

}
