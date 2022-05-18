/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.trabajotaw.trabajotaw.entity;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author nicol
 */
@Entity
@Table(name = "PUJA")
 
@NamedQueries({
    @NamedQuery(name = "Puja.findAll", query = "SELECT p FROM Puja p")
    , @NamedQuery(name = "Puja.findByIdPuja", query = "SELECT p FROM Puja p WHERE p.idPuja = :idPuja")
    , @NamedQuery(name = "Puja.findByCantidad", query = "SELECT p FROM Puja p WHERE p.cantidad = :cantidad")})
public class Puja   {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PUJA")
    private Integer idPuja;
    @Basic(optional = false)
     
    @Column(name = "CANTIDAD")
    private double cantidad;
    @JoinColumn(name = "PRODUCTO", referencedColumnName = "ID_PRODUCTO")
    @ManyToOne(optional = false)
    private Producto producto;
    @JoinColumn(name = "COMPRADOR", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private Usuario comprador;

    public Puja() {
    }

    public Puja(Integer idPuja) {
        this.idPuja = idPuja;
    }

    public Puja(Integer idPuja, double cantidad) {
        this.idPuja = idPuja;
        this.cantidad = cantidad;
    }

    public Integer getIdPuja() {
        return idPuja;
    }

    public void setIdPuja(Integer idPuja) {
        this.idPuja = idPuja;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public void setComprador(Usuario comprador) {
        this.comprador = comprador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPuja != null ? idPuja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Puja)) {
            return false;
        }
        Puja other = (Puja) object;
        if ((this.idPuja == null && other.idPuja != null) || (this.idPuja != null && !this.idPuja.equals(other.idPuja))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "trabajoTAW.entity.Puja[ idPuja=" + idPuja + " ]";
    }

    
}
