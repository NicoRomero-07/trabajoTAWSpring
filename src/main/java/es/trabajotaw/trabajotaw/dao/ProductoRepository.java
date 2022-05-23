package es.trabajotaw.trabajotaw.dao;

import es.trabajotaw.trabajotaw.dto.ProductoDTO;
import es.trabajotaw.trabajotaw.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query("select p FROM Producto p where p.publicador.idUsuario = :publicadorid")
    List<Producto> listaProductosPublicadorId(@Param("publicadorid") Integer publicadorid);

    List<Producto> findByNombreProducto(String busqueda);
}
