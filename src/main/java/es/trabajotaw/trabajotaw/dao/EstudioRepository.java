package es.trabajotaw.trabajotaw.dao;

import es.trabajotaw.trabajotaw.entity.Estudio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EstudioRepository extends JpaRepository<Estudio, Integer> {
    @Query("select e from Estudio e where upper(e.nombre) like upper(:nombre)")
    List<Estudio> findByNombre(@Param("nombre") String nombre);
}
