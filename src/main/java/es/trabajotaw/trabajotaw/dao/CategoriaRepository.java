package es.trabajotaw.trabajotaw.dao;

import es.trabajotaw.trabajotaw.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    @Query("select c from Categoria c where UPPER(c.nombre) like UPPER(:nombre) ")
    List<Categoria> findByNombreCategoria(String nombre);
}
