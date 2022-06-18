package es.trabajotaw.trabajotaw.dao;

import es.trabajotaw.trabajotaw.entity.ListaProducto;
import es.trabajotaw.trabajotaw.entity.ListaProductoPK;
import es.trabajotaw.trabajotaw.entity.Producto;
import es.trabajotaw.trabajotaw.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListaProductoRepository extends JpaRepository<ListaProducto, ListaProductoPK> {

    ListaProducto findByProducto1AndUsuario1(Producto p, Usuario u);
}
