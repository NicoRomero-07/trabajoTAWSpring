package es.trabajotaw.trabajotaw.dao;

import es.trabajotaw.trabajotaw.entity.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoUsuarioRepositoryCustom {
    List<TipoUsuario> findByTipo(String filtroNombre);
}
