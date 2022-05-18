package es.trabajotaw.trabajotaw.dao;

import es.trabajotaw.trabajotaw.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
