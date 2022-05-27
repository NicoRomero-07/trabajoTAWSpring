/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.trabajotaw.trabajotaw.dto;



/**
 *
 * @author Alfonso 100%
 */
public class EstudioDTO {
    private Integer idEstudio;
    private String nombre;
    private Integer analista;
    private String descripcion;
    private Boolean comprador;
    private Boolean vendedor;

    public Integer getIdEstudio() {
        return idEstudio;
    }

    public void setIdEstudio(Integer idEstudio) {
        this.idEstudio = idEstudio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnalista() {
        return analista;
    }

    public void setAnalista(Integer analista) {
        this.analista = analista;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getComprador() {
        return comprador;
    }

    public void setComprador(Boolean comprador) {
        this.comprador = comprador;
    }

    public Boolean getVendedor() {
        return vendedor;
    }

    public void setVendedor(Boolean vendedor) {
        this.vendedor = vendedor;
    }

    public Boolean getProducto() {
        return producto;
    }

    public void setProducto(Boolean producto) {
        this.producto = producto;
    }

    public Integer getDatosEstudioUsuario() {
        return datosEstudioUsuario;
    }

    public void setDatosEstudioUsuario(Integer datosEstudioUsuario) {
        this.datosEstudioUsuario = datosEstudioUsuario;
    }

    public Integer getDatosEstudioProducto() {
        return datosEstudioProducto;
    }

    public void setDatosEstudioProducto(Integer datosEstudioProducto) {
        this.datosEstudioProducto = datosEstudioProducto;
    }

    private Boolean producto;
    private Integer datosEstudioUsuario;
    private Integer datosEstudioProducto;

    public EstudioDTO() {
    }

}
