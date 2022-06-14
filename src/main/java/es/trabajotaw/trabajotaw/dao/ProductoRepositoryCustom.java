package es.trabajotaw.trabajotaw.dao;

import es.trabajotaw.trabajotaw.entity.DatosEstudioProducto;
import es.trabajotaw.trabajotaw.entity.Producto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepositoryCustom {
    List<Producto> visualizarEstudio(DatosEstudioProducto estudioProducto);
}
