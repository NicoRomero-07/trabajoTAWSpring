package es.trabajotaw.trabajotaw.dao;

import es.trabajotaw.trabajotaw.entity.DatosEstudioProducto;
import es.trabajotaw.trabajotaw.entity.Producto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;
import javax.persistence.Query;

public class ProductoRepositoryImpl implements ProductoRepositoryCustom{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Producto> visualizarEstudio(DatosEstudioProducto estudioProducto) {
        Query q;
        String consulta = generarConsulta(estudioProducto);
        q = em.createQuery(consulta);
        return q.getResultList();
    }

    private String generarConsulta(DatosEstudioProducto estudioProducto){
        StringBuilder consulta = new StringBuilder();

        Double dprecioSalida = estudioProducto.getPrecioSalida();
        Double dprecioActual = estudioProducto.getPrecioActual();
        Boolean bpromocion = estudioProducto.getPromocion();
        Boolean bvendidos = estudioProducto.getVendidos();
        Boolean bcategorias = estudioProducto.getCategorias();

        consulta.append("SELECT p FROM Producto p ");
        if(bcategorias){
            consulta.append(" JOIN Categoria c ON c.idCategoria = p.categoria ");
        }
        if(dprecioActual != null){
            consulta.append(" JOIN Puja pu ON p.idProducto = pu.producto.idProducto ");
        }
        consulta.append(" WHERE ");
        consulta.append(dprecioSalida != null ? " p.precioSalida >= " + dprecioSalida  + " AND " : "");
        consulta.append(dprecioActual != null ? "pu.cantidad IN "
                + "(SELECT MAX(pu.cantidad) FROM Puja pu GROUP BY pu.producto) "
                + "AND pu.cantidad >= " + dprecioActual + " AND " : "");
        consulta.append(Objects.equals(bpromocion, Boolean.TRUE) ? " p.enPromocion = " + bpromocion + " AND " : " p.enPromocion = " + bpromocion + " AND ");
        consulta.append(Objects.equals(bvendidos, Boolean.FALSE) ? " p.comprador IS NULL AND " : " p.comprador IS NOT NULL AND");

        quitarAND(consulta);

        if(bcategorias){
            consulta.append(Objects.equals(bcategorias, Boolean.TRUE) ? " ORDER BY p.categoria" : "");
        }
        return consulta.toString();
    }

    private void quitarAND(StringBuilder consulta){
        consulta.deleteCharAt(consulta.length() -1);
        consulta.deleteCharAt(consulta.length() -1);
        consulta.deleteCharAt(consulta.length() -1);
        consulta.deleteCharAt(consulta.length() -1);
    }

}
