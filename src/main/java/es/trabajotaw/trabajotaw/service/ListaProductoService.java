package es.trabajotaw.trabajotaw.service;

import es.trabajotaw.trabajotaw.dao.ListaProductoRepository;
import es.trabajotaw.trabajotaw.dao.ProductoRepository;
import es.trabajotaw.trabajotaw.dao.UsuarioRepository;
import es.trabajotaw.trabajotaw.entity.ListaProducto;
import es.trabajotaw.trabajotaw.entity.Producto;
import es.trabajotaw.trabajotaw.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListaProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ListaProductoRepository listaProductoRepository;

    public void nuevoProductoFavorito(Integer idProducto, Integer idUsuario){

        Usuario u = usuarioRepository.findById(idUsuario).orElse(null);
        Producto p = productoRepository.getById(idProducto);

        ListaProducto lp = new ListaProducto(p.getIdProducto(), u.getIdUsuario());

        lp.setUsuario1(u);
        lp.setProducto1(p);

        listaProductoRepository.save(lp);
    }

    public ListaProducto buscarListaProducto(Integer idProducto, Integer idUsuario){
        Usuario u = usuarioRepository.findById(idUsuario).orElse(null);
        Producto p = productoRepository.getById(idProducto);

        ListaProducto lp = listaProductoRepository.findByProducto1AndUsuario1(p,u);
        return lp;
    }

    public void borrarProductoFavorito(Integer idProducto, Integer idUsuario){

        Usuario u = usuarioRepository.findById(idUsuario).orElse(null);
        Producto p = productoRepository.getById(idProducto);

        ListaProducto lp = buscarListaProducto(p.getIdProducto(), u.getIdUsuario());

        listaProductoRepository.delete(lp);
    }
}
