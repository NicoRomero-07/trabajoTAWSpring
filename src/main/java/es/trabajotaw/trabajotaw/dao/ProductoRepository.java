package es.trabajotaw.trabajotaw.dao;

import es.trabajotaw.trabajotaw.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query("select p FROM Producto p where p.publicador.idUsuario = :publicadorid")
    List<ProductoDTO> listaProductosPublicadorId(@Param("publicadorid") Integer publicadorid);
}
