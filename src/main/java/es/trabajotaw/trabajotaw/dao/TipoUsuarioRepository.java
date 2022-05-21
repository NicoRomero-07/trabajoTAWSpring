package es.trabajotaw.trabajotaw.dao;

import es.trabajotaw.trabajotaw.entity.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Integer> {
    TipoUsuario findByTipo(String tipo);
}
