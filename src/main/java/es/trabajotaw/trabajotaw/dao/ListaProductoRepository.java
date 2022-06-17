package es.trabajotaw.trabajotaw.dao;

import es.trabajotaw.trabajotaw.entity.ListaProducto;
import es.trabajotaw.trabajotaw.entity.ListaProductoPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListaProductoRepository extends JpaRepository<ListaProducto, ListaProductoPK> {

}
