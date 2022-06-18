package es.trabajotaw.trabajotaw.dao;

import es.trabajotaw.trabajotaw.entity.Producto;
import es.trabajotaw.trabajotaw.entity.Puja;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PujaRepository extends JpaRepository<Puja, Integer> {

    List<Puja> findByProducto(Producto p);
}
