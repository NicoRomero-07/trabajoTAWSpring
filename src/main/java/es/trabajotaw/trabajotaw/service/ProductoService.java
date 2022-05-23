/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.trabajotaw.trabajotaw.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.trabajotaw.trabajotaw.dao.ProductoRepository;
import es.trabajotaw.trabajotaw.dao.UsuarioRepository;
import es.trabajotaw.trabajotaw.dto.ProductoDTO;
import es.trabajotaw.trabajotaw.entity.Producto;
import es.trabajotaw.trabajotaw.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Victor (58%) Alfonso (9%) Pablo (9%)
 */

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @EJB DatosEstudioProductoFacade  depf;
    
    public List<ProductoDTO> listaEntityADTO(List<Producto> lista) {
        List<ProductoDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Producto p:lista) {
                listaDTO.add(p.toDTO());
            }
        }
        return listaDTO;
    }
    
    public List<ProductoDTO> listarProductos(String busqueda) {
        List<Producto> productos = null;

        if (busqueda == null || busqueda.isEmpty()) {
            productos = this.productoRepository.findAll();
        } else {
            productos = this.productoRepository.findByNombreProducto(busqueda);
        }
        
        return this.listaEntityADTO(productos);                
    } 

    
    private void rellenarProducto (Producto producto,
                              String nombreProducto, String descripcion, Double precioSalida, String imagen, Date fechaInicio, Date fechaFin, String comprador, String publicador, Boolean promocion,  Integer categoria) {
        
        producto.setNombre(nombreProducto);
        producto.setDescripcion(descripcion);
        producto.setPrecioSalida(precioSalida);
        producto.setUrlFoto(imagen);
        producto.setFechaInicioSubasta(fechaInicio);
        producto.setFechaFinSubasta(fechaFin);
        
        if(!"".equals(comprador)){
            List<Usuario> usuarios = usuarioRepository.findByNombreUsuario(comprador);
            if(!usuarios.isEmpty()){
               Usuario compradorUser = usuarios.get(0);
               producto.setComprador(compradorUser); 
            }
        }else{
            producto.setComprador(null);
        }

        if(!"".equals(publicador)){
            List<Usuario> usuarios = usuarioRepository.findByNombreUsuario(publicador);
            if(!usuarios.isEmpty()){
                Usuario publicadorUser = usuarios.get(0);
                producto.setPublicador(publicadorUser);
            }
        }
        
        producto.setEnPromocion(promocion);
          
        producto.setCategoria(categoria);
                      
    }
    
    public void crearProducto (String nombreProducto, String descripcion, Double precioSalida, String imagen, Date fechaInicio, Date fechaFin, String comprador, String publicador, Boolean promocion,  Integer categoria) {
        Producto producto = new Producto();

        this.rellenarProducto(producto, nombreProducto, descripcion, precioSalida, imagen, fechaInicio, fechaFin, comprador, publicador, promocion, categoria);

        this.productoRepository.save(producto);
    }

    public void modificarProducto (Integer id,
                              String nombreProducto, String descripcion, Double precioSalida, String imagen, Date fechaInicio, Date fechaFin, String comprador, String publicador, Boolean promocion, Integer categoria) {
        
        Producto producto = this.productoRepository.find(id);

        this.rellenarProducto(producto, nombreProducto, descripcion, precioSalida, imagen, fechaInicio, fechaFin, comprador, publicador, promocion, categoria);

        this.productoRepository.save(producto);
    }
    
    public ProductoDTO buscarProducto(Integer id){
        Producto p = productoRepository.find(id);
        return p.toDTO();
    }

    public List<ProductoDTO> listaProductosLogin(Integer idUsuario) {
        return this.listaEntityADTO(this.productoRepository.listaProductosPublicadorId(idUsuario));
    }
    
    public void borrarProducto (Integer id) {
        Producto producto = this.productoRepository.find(id);

        this.productoRepository.delete(producto);
    }
    
    public List<ProductoDTO> buscarProductosComprados(Integer idProducto){
        List<Producto> productos = productoRepository.productosComprados(idProducto);
        return this.listaEntityADTO(productos);
    }
    

    public List<ProductoDTO> buscarProductosPujados(Integer idUsuario){
        List<Producto> productos = productoRepository.productosPujados(idUsuario);
        return this.listaEntityADTO(productos);
    }
    

    public List<ProductoDTO> filtrarProductosComprados(Integer idProducto, String filtro){
        List<Producto> productos = null;

        if (filtro == null || filtro.isEmpty()) {
            productos = this.productoRepository.filtrarProductosComprados(idProducto, null);
        } else {
            productos = this.productoRepository.filtrarProductosComprados(idProducto, filtro);
        }
        
        return this.listaEntityADTO(productos); 
    }
    
    public List<ProductoDTO> visualizarEstudio(Integer idEstudioProducto){
        DatosEstudioProducto estudioProducto = this.depf.find(idEstudioProducto);
        List<Producto> productos = productoRepository.visualizarEstudio(estudioProducto);
        List<ProductoDTO> productosDTO = this.listaEntityADTO(productos);
        return productosDTO;
    }
    public List<ProductoDTO> getProductosEnPromocion(){
        return this.listaEntityADTO(this.productoRepository.getProductosPromocion());
    }
}
