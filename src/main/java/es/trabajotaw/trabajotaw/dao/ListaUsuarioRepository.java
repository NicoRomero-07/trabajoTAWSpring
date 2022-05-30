package es.trabajotaw.trabajotaw.dao;

import es.trabajotaw.trabajotaw.dto.ListaUsuarioDTO;
import es.trabajotaw.trabajotaw.entity.ListaUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListaUsuarioRepository extends JpaRepository<ListaUsuario,Integer> {
    List<ListaUsuario> findByNombreContaining(String nombre);
}
