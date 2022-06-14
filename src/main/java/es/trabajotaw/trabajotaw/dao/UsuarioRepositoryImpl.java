package es.trabajotaw.trabajotaw.dao;

import es.trabajotaw.trabajotaw.entity.DatosEstudioUsuario;
import es.trabajotaw.trabajotaw.entity.Estudio;
import es.trabajotaw.trabajotaw.entity.Usuario;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Objects;

public class UsuarioRepositoryImpl implements UsuarioRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Usuario> visualizarEstudio(Estudio estudio, DatosEstudioUsuario estudioUsuario) {
        Query q;
        String comprador = "comprador";
        String vendedor = "vendedor";
        String consulta = generarConsulta(estudio,estudioUsuario);
        q = em.createQuery(consulta);
        if(consulta.contains(":comprador")){
            q.setParameter("comprador", '%' + comprador + '%');
        }
        if(consulta.contains(":vendedor")){
            q.setParameter("vendedor", '%' + vendedor + '%');
        }
        return q.getResultList();
    }

    private String generarConsulta(Estudio estudio,DatosEstudioUsuario estudioUsuario) {
        StringBuilder consulta = new StringBuilder();
        Boolean bnombre = estudioUsuario.getNombre();
        Boolean bapellidos = estudioUsuario.getApellidos();
        Boolean bingresos = estudioUsuario.getIngresos();
        Boolean bascendente = estudioUsuario.getAscendente();

        consulta.append("SELECT u FROM Usuario u");

        if(bingresos){
            if(estudio.getComprador()){
                consulta.append(" JOIN Producto p ON u.idUsuario = p.comprador.idUsuario ");
            }else{
                consulta.append(" JOIN Producto p ON u.idUsuario = p.publicador.idUsuario ");
            }
            consulta.append(" JOIN Puja pu ON p.idProducto = pu.producto.idProducto");

        }

        if(estudio.getVendedor()){
            consulta.append(" WHERE upper(u.tipoUsuario.tipo) like upper(:vendedor)");
        }else{
            consulta.append(" WHERE upper(u.tipoUsuario.tipo) like upper(:comprador)");
        }
        if(bingresos && estudio.getComprador()){
            consulta.append(" AND pu.comprador.idUsuario = u.idUsuario GROUP BY u.idUsuario,u.nombreUsuario,u.contrasenya,"
                                        + "u.email,u.nombre,u.primerApellido,u.segundoApellido,u.fechaNacimiento,u.sexo,u.direccion,u.tipoUsuario");
        }else if(bingresos && estudio.getVendedor()){
            consulta.append(" AND pu.cantidad IN "
                    + "(SELECT MAX(pu.cantidad) FROM Usuario u "
                    + "JOIN Producto p ON u.idUsuario = p.publicador.idUsuario "
                    + "JOIN Puja pu ON p.idProducto = pu.producto.idProducto "
                    + "WHERE upper(u.tipoUsuario.tipo) like upper(:vendedor) "
                    + "GROUP BY p.idProducto) "
                    + "GROUP BY u.idUsuario,u.nombreUsuario,u.contrasenya,"
                    + "u.email,u.nombre,u.primerApellido,u.segundoApellido,u.fechaNacimiento,u.sexo,u.direccion,u.tipoUsuario");
        }


        if (bnombre || bapellidos || bingresos) {
            consulta.append(" ORDER BY ");
        }

        String ascendente = Objects.equals(bascendente, Boolean.TRUE) ? " ASC, " : " DESC, ";

        consulta.append(Objects.equals(bnombre, Boolean.TRUE) ? "u.nombre" + ascendente : "");
        consulta.append(Objects.equals(bapellidos, Boolean.TRUE) ? "u.primerApellido" + ascendente + "u.segundoApellido" + ascendente : "");

        consulta.append(Objects.equals(bingresos, Boolean.TRUE) ? "sum(pu.cantidad)" + ascendente : "");

        if (bnombre || bapellidos || bingresos) {
            consulta.deleteCharAt(consulta.length() - 1);
            consulta.deleteCharAt(consulta.length() - 1);
        }


        return consulta.toString();
    }

    @Override
    public List<Double> getIngresosUsuarios(Estudio estudio, DatosEstudioUsuario estudioUsuario) {
        Query q;
        String comprador = "comprador";
        String vendedor = "vendedor";
        String consulta = generarConsultaIngresos(estudio,estudioUsuario);
        q = em.createQuery(consulta);
        if(consulta.contains(":comprador")){
            q.setParameter("comprador", '%' + comprador + '%');
        }
        if(consulta.contains(":vendedor")){
            q.setParameter("vendedor", '%' + vendedor + '%');
        }
        return q.getResultList();
    }

    private String generarConsultaIngresos(Estudio estudio,DatosEstudioUsuario estudioUsuario){
        StringBuilder consulta = new StringBuilder();
        String ascendente = Objects.equals(estudioUsuario.getAscendente(), Boolean.TRUE) ? " ASC, " : " DESC, ";
        if(estudio.getComprador()){
            consulta.append("SELECT SUM(pu.cantidad) FROM Usuario u JOIN Producto p ON u.idUsuario = p.comprador.idUsuario " +
                    "JOIN Puja pu ON p.idProducto = pu.producto.idProducto " +
                    "WHERE upper(u.tipoUsuario.tipo) like upper(:comprador) " +
                    "AND pu.comprador.idUsuario = u.idUsuario " +
                    "GROUP BY u ORDER BY SUM(pu.cantidad)" + ascendente);
        }else{
            consulta.append("SELECT SUM(pu.cantidad) FROM Usuario u JOIN Producto p ON u.idUsuario = p.publicador.idUsuario " +
                    "JOIN Puja pu ON p.idProducto = pu.producto.idProducto " +
                    "WHERE upper(u.tipoUsuario.tipo) like upper(:vendedor) " +
                    "AND pu.cantidad IN "
                    + "(SELECT MAX(pu.cantidad) FROM Usuario u JOIN Producto p ON u.idUsuario = p.publicador.idUsuario " +
                    "JOIN Puja pu ON p.idProducto = pu.producto.idProducto " +
                    "WHERE upper(u.tipoUsuario.tipo) like upper(:vendedor) " +
                    "GROUP BY p.idProducto)" +
                    "GROUP BY u ORDER BY SUM(pu.cantidad)" + ascendente);
        }
        consulta.append(Objects.equals(estudioUsuario.getNombre(), Boolean.TRUE) ? "u.nombre" + ascendente : "");
        consulta.append(Objects.equals(estudioUsuario.getApellidos(), Boolean.TRUE) ? "u.primerApellido" + ascendente + "u.segundoApellido" + ascendente : "");

        consulta.deleteCharAt(consulta.length() - 1);
        consulta.deleteCharAt(consulta.length() - 1);

        return consulta.toString();
    }

    public Usuario getUsuarioPujaMax(Integer idProducto) {
        Query q1 = em.createQuery("select MAX(p.cantidad) from Puja p where p.producto.idProducto = :idProducto");
        q1.setParameter("idProducto", idProducto);
        double cantidad = (double) q1.getSingleResult();
        Query q2 = em.createQuery("select u from Usuario u join u.pujaList pl where pl.cantidad = :cantidad and pl.producto.idProducto = :idProducto");
        q2.setParameter("cantidad", cantidad);
        q2.setParameter("idProducto", idProducto);
        return (Usuario) q2.getResultList().get(0);
    }


}
