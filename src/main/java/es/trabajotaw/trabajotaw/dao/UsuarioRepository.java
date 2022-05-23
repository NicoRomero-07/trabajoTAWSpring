package es.trabajotaw.trabajotaw.dao;

import es.trabajotaw.trabajotaw.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("select u from Usuario u where upper(u.nombreUsuario) like upper(:usuario) and upper(u.contrasenya) like upper(:clave)")
    Usuario comprobarUsuario(@Param("usuario") String nombreUsuario, @Param("clave") String clave);

}
