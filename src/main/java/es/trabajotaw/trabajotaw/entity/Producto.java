/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.trabajotaw.trabajotaw.entity;

import es.trabajotaw.trabajotaw.dto.ProductoDTO;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 *
 * @author nicol
 */
@Entity
@Table(name = "PRODUCTO")
 
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
    , @NamedQuery(name = "Producto.findByIdProducto", query = "SELECT p FROM Producto p WHERE p.idProducto = :idProducto")
    , @NamedQuery(name = "Producto.findByNombre", query = "SELECT p FROM Producto p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Producto.findByDescripcion", query = "SELECT p FROM Producto p WHERE p.descripcion = :descripcion")
    , @NamedQuery(name = "Producto.findByPrecioSalida", query = "SELECT p FROM Producto p WHERE p.precioSalida = :precioSalida")
    , @NamedQuery(name = "Producto.findByUrlFoto", query = "SELECT p FROM Producto p WHERE p.urlFoto = :urlFoto")
    , @NamedQuery(name = "Producto.findByCategoria", query = "SELECT p FROM Producto p WHERE p.categoria = :categoria")
    , @NamedQuery(name = "Producto.findByPublicador", query = "SELECT p FROM Producto p WHERE p.publicador = :publicador")
    , @NamedQuery(name = "Producto.findByEnPromocion", query = "SELECT p FROM Producto p WHERE p.enPromocion = :enPromocion")
    , @NamedQuery(name = "Producto.findByFechaInicioSubasta", query = "SELECT p FROM Producto p WHERE p.fechaInicioSubasta = :fechaInicioSubasta")
    , @NamedQuery(name = "Producto.findByFechaFinSubasta", query = "SELECT p FROM Producto p WHERE p.fechaFinSubasta = :fechaFinSubasta")
    , @NamedQuery(name = "Producto.findByComprador", query = "SELECT p FROM Producto p WHERE p.comprador = :comprador")})
public class Producto   {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PRODUCTO")
    private Integer idProducto;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
     
    @Column(name = "PRECIO_SALIDA")
    private double precioSalida;
    @Column(name = "URL_FOTO")
    private String urlFoto;
    @Basic(optional = false)
     
    @Column(name = "CATEGORIA")
    private int categoria;
    @JoinColumn(name = "PUBLICADOR", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
     
    private Usuario publicador;
    @Basic(optional = false)
     
    @Column(name = "EN_PROMOCION")
    private Boolean enPromocion;
     
    @Column(name = "FECHA_INICIO_SUBASTA")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioSubasta;
     
    @Column(name = "FECHA_FIN_SUBASTA")
    @Temporal(TemporalType.DATE)
    private Date fechaFinSubasta;
    @JoinColumn(name = "COMPRADOR", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = true)
    
    private Usuario comprador;
    @ManyToMany(mappedBy = "productoList")
    private List<Categoria> categoriaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto1")
    private List<ListaProducto> listaProductoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto")
    private List<Puja> pujaList;

    public Producto() {
    }

    public Producto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Producto(Integer idProducto, String nombre, double precioSalida, int categoria, Usuario publicador, Boolean enPromocion,
            Date fechaInicioSubasta, Date fechaFinSubasta, Usuario comprador) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precioSalida = precioSalida;
        this.categoria = categoria;
        this.publicador = publicador;
        this.enPromocion = enPromocion;
        this.fechaInicioSubasta = fechaInicioSubasta;
        this.fechaFinSubasta = fechaFinSubasta;
        this.comprador = comprador;
    }

    public Producto(ProductoDTO producto) {
        this.idProducto = producto.getIdProducto();
        this.nombre = producto.getNombre();
        this.precioSalida = producto.getPrecioSalida();
        this.categoria = producto.getCategoria();
        this.descripcion = producto.getDescripcion();
        this.publicador = new Usuario(producto.getPublicador());
        if(producto.getComprador()!=null)  this.comprador = new Usuario(producto.getComprador());
        this.enPromocion = producto.getEnPromocion();
        this.fechaInicioSubasta = producto.getFechaInicioSubasta();
        this.fechaFinSubasta = producto.getFechaFinSubasta();
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioSalida() {
        return precioSalida;
    }

    public void setPrecioSalida(double precioSalida) {
        this.precioSalida = precioSalida;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public Usuario getPublicador() {
        return publicador;
    }

    public void setPublicador(Usuario publicador) {
        this.publicador = publicador;
    }

    public Boolean getEnPromocion() {
        return enPromocion;
    }

    public void setEnPromocion(Boolean enPromocion) {
        this.enPromocion = enPromocion;
    }
    
    public Date getFechaInicioSubasta(){
        return this.fechaInicioSubasta;
    }
    
    public void setFechaInicioSubasta(Date fechaInicioSubasta){
        this.fechaInicioSubasta = fechaInicioSubasta;
    }
    
    public Date getFechaFinSubasta(){
        return this.fechaInicioSubasta;
    }
    
    public void setFechaFinSubasta(Date fechaFinSubasta){
        this.fechaFinSubasta = fechaFinSubasta;
    }
    
    public Usuario getComprador() {
        return comprador;
    }

    public void setComprador(Usuario comprador) {
        this.comprador = comprador;
    }
    
    

     
    public List<Categoria> getCategoriaList() {
        return categoriaList;
    }

    public void setCategoriaList(List<Categoria> categoriaList) {
        this.categoriaList = categoriaList;
    }

     
    public List<ListaProducto> getListaProductoList() {
        return listaProductoList;
    }

    public void setListaProductoList(List<ListaProducto> listaProductoList) {
        this.listaProductoList = listaProductoList;
    }

     
    public List<Puja> getPujaList() {
        return pujaList;
    }

    public void setPujaList(List<Puja> pujaList) {
        this.pujaList = pujaList;
    }

    public ProductoDTO toDTO() {
        ProductoDTO dto = new ProductoDTO();

        dto.setIdProducto(idProducto);
        dto.setNombre(nombre);
        dto.setDescripcion(descripcion);
        dto.setCategoria(categoria);
        dto.setUrlFoto(urlFoto);
        dto.setPublicador(publicador.toDTO());
        dto.setPrecioSalida(precioSalida);
        dto.setFechaInicioSubasta(fechaInicioSubasta);
        dto.setFechaFinSubasta(fechaFinSubasta);
        dto.setEnPromocion(enPromocion);
        if(comprador != null){
            dto.setComprador(comprador.toDTO());
        }

        return dto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProducto != null ? idProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.idProducto == null && other.idProducto != null) || (this.idProducto != null && !this.idProducto.equals(other.idProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.trabajotaw.entity.Producto[ idProducto=" + idProducto + " ]";
    }
    
    
}
