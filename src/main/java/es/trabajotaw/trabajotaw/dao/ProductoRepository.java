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

    @Query("select p from Producto p")
    List<Producto> listaProductos();

    @Query("select p FROM Producto p where p.publicador.idUsuario = :publicadorid")
    List<Producto> listaProductosPublicadorId(@Param("publicadorid") Integer publicadorid);

    @Query("select p from Producto p where upper(p.nombre) like upper(:nombre)")
    List<Producto> findByNombreContaining(String nombre);

    @Query("select p from Producto p where p.comprador.idUsuario = :idUsuario")
    List<Producto> listaProductosComprados(@Param("idUsuario") Integer idUsuario);

    @Query("select p from Producto p where p.comprador.idUsuario = :idUsuario and"
            + " upper(p.nombre) like upper(:filtro)")
    List<Producto> filtrarProductosComprados(@Param("idUsuario") Integer idUsuario, @Param("filtro") String filtro);

    @Query("select p from Producto p join Puja pu on"
            + " p.idProducto = pu.producto.idProducto where pu.comprador.idUsuario = :idUsuario")
    List<Producto> listaProductosPujados(@Param("idUsuario") Integer idUsuario);

    @Query("select p from Producto p join ListaProducto lp on"
            + " p.idProducto = lp.producto1.idProducto where lp.usuario1.idUsuario = :idUsuario")
    List<Producto> listaProductosFavoritos(@Param("idUsuario") Integer idUsuario);

    @Query("select p from Producto p join ListaProducto lp on"
            + " p.idProducto = lp.producto1.idProducto where lp.usuario1.idUsuario = :idUsuario and"
            + " upper(lp.producto1.nombre) like upper(:filtro)")
    List<Producto> filtrarProductosFavoritos(@Param("idUsuario") Integer idUsuario, @Param("filtro") String filtro);
    List<Producto> findByEnPromocion(boolean enPromocion);

}
