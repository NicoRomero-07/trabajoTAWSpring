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
@Table(name = "ESTUDIO")
 
@NamedQueries({
    @NamedQuery(name = "Estudio.findAll", query = "SELECT e FROM Estudio e")
    , @NamedQuery(name = "Estudio.findByIdEstudio", query = "SELECT e FROM Estudio e WHERE e.idEstudio = :idEstudio")
    , @NamedQuery(name = "Estudio.findByNombre", query = "SELECT e FROM Estudio e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Estudio.findByAnalista", query = "SELECT e FROM Estudio e WHERE e.analista = :analista")
    , @NamedQuery(name = "Estudio.findByDescripcion", query = "SELECT e FROM Estudio e WHERE e.descripcion = :descripcion")})
public class Estudio   {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ESTUDIO")
    private Integer idEstudio;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @JoinColumn(name = "ANALISTA", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private Usuario analista;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "COMPRADOR")
    private Boolean comprador;
    @Basic(optional = false)
    @Column(name = "VENDEDOR")
    private Boolean vendedor;
    @Basic(optional = false)
    @Column(name = "PRODUCTO")
    private Boolean producto;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "estudio")
    private DatosEstudioUsuario datosEstudioUsuario;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "estudio")
    private DatosEstudioProducto datosEstudioProducto;

    public Estudio() {
    }

    public Estudio(Integer idEstudio) {
        this.idEstudio = idEstudio;
    }

    public Estudio(Integer idEstudio, String nombre, Usuario analista) {
        this.idEstudio = idEstudio;
        this.nombre = nombre;
        this.analista = analista;
    }

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

    public Usuario getAnalista() {
        return analista;
    }

    public void setAnalista(Usuario analista) {
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
    
    public DatosEstudioUsuario getDatosEstudioUsuario() {
        return datosEstudioUsuario;
    }

    public void setDatosEstudioUsuario(DatosEstudioUsuario datosEstudioUsuario) {
        this.datosEstudioUsuario = datosEstudioUsuario;
    }

    public DatosEstudioProducto getDatosEstudioProducto() {
        return datosEstudioProducto;
    }

    public void setDatosEstudioProducto(DatosEstudioProducto datosEstudioProducto) {
        this.datosEstudioProducto = datosEstudioProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstudio != null ? idEstudio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estudio)) {
            return false;
        }
        Estudio other = (Estudio) object;
        if ((this.idEstudio == null && other.idEstudio != null) || (this.idEstudio != null && !this.idEstudio.equals(other.idEstudio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "trabajoTAW.entity.Estudio[ idEstudio=" + idEstudio + " ]";
    }
    
    


    
    
}
