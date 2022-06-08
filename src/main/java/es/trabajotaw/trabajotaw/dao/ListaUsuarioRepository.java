package es.trabajotaw.trabajotaw.dao;

import es.trabajotaw.trabajotaw.dto.ListaUsuarioDTO;
import es.trabajotaw.trabajotaw.entity.ListaUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListaUsuarioRepository extends JpaRepository<ListaUsuario,Integer> {
    @Query("SELECT l FROM ListaUsuario l WHERE UPPER(l.nombre) LIKE UPPER (:nombre)")
    List<ListaUsuario> findByNombreContainsIgnoreCase(@Param("nombre") String nombre);
}
