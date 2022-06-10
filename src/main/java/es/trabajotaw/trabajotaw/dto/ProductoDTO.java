/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.trabajotaw.trabajotaw.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 *
 * @author Victor
 */
public class ProductoDTO {


    private Integer idProducto;
    private String nombre;
    private String descripcion;
    private double precioSalida;
    private String urlFoto;
    private int categoria;
    private UsuarioDTO publicador;
    private boolean enPromocion;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date fechaInicioSubasta;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date fechaFinSubasta;
    private UsuarioDTO comprador;
    private String compradorNombre;
    private String publicadorNombre;
    
            
    public ProductoDTO() {
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

    public UsuarioDTO getPublicador() {
        return publicador;
    }

    public void setPublicador(UsuarioDTO publicador) {
        this.publicador = publicador;
        this.publicadorNombre = publicador.getNombreUsuario();
    }

    public Boolean getEnPromocion() {
        return enPromocion;
    }

    public void setEnPromocion(Boolean enPromocion) {
        this.enPromocion = enPromocion;
    }
    
    
    public UsuarioDTO getComprador() {
        return comprador;
    }

    public void setComprador(UsuarioDTO comprador) {
        this.comprador = comprador;
        this.compradorNombre = comprador.getNombreUsuario();
    }
    
    public Date getFechaInicioSubasta(){
        return fechaInicioSubasta;
    }
    
    public void setFechaInicioSubasta(Date fechaInicioSubasta){
        this.fechaInicioSubasta = fechaInicioSubasta;
    }
    
    public Date getFechaFinSubasta(){
        return fechaFinSubasta;
    }
    
    public void setFechaFinSubasta(Date fechaFinSubasta){
        this.fechaFinSubasta = fechaFinSubasta;
    }

    public String getCompradorNombre() {
        return compradorNombre;
    }

    public void setCompradorNombre(String compradorNombre) {
        this.compradorNombre = compradorNombre;
    }

    public String getPublicadorNombre() {
        return publicadorNombre;
    }

    public void setPublicadorNombre(String publicadorNombre) {
        this.publicadorNombre = publicadorNombre;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductoDTO)) {
            return false;
        }
        ProductoDTO other = (ProductoDTO) object;
        if ((this.idProducto == null && other.idProducto != null) || (this.idProducto != null && !this.idProducto.equals(other.idProducto))) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProducto != null ? idProducto.hashCode() : 0);
        return hash;
    }

}
