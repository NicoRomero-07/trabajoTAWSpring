package es.trabajotaw.trabajotaw.dao;

import es.trabajotaw.trabajotaw.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    List<Producto> findByEnPromocion(boolean enPromocion);
}
