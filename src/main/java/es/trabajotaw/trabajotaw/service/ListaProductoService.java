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
    private UsuarioRepository usuarioRepository;
    private ListaProductoRepository listaProductoRepository;

    public void nuevoProductoFavorito(Integer idProducto, Integer idUsuario){
        ListaProducto lp = new ListaProducto();

        Usuario u = usuarioRepository.findById(idUsuario).orElse(null);
        Producto p = productoRepository.getById(idProducto);

        lp.setUsuario1(u);
        lp.setProducto1(p);

        listaProductoRepository.save(lp);
    }
}
