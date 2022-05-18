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
@Table(name = "DATOS_ESTUDIO_USUARIO")
 
@NamedQueries({
    @NamedQuery(name = "DatosEstudioUsuario.findAll", query = "SELECT d FROM DatosEstudioUsuario d")
    , @NamedQuery(name = "DatosEstudioUsuario.findById", query = "SELECT d FROM DatosEstudioUsuario d WHERE d.id = :id")
    , @NamedQuery(name = "DatosEstudioUsuario.findByNombre", query = "SELECT d FROM DatosEstudioUsuario d WHERE d.nombre = :nombre")
    , @NamedQuery(name = "DatosEstudioUsuario.findByApellidos", query = "SELECT d FROM DatosEstudioUsuario d WHERE d.apellidos = :apellidos")
    , @NamedQuery(name = "DatosEstudioUsuario.findByIngresos", query = "SELECT d FROM DatosEstudioUsuario d WHERE d.ingresos = :ingresos")
    , @NamedQuery(name = "DatosEstudioUsuario.findByAscendente", query = "SELECT d FROM DatosEstudioUsuario d WHERE d.ascendente = :ascendente")})
public class DatosEstudioUsuario   {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
     
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
     
    @Column(name = "NOMBRE")
    private Boolean nombre;
    @Basic(optional = false)
     
    @Column(name = "APELLIDOS")
    private Boolean apellidos;
    @Basic(optional = false)
     
    @Column(name = "INGRESOS")
    private Boolean ingresos;
    @Basic(optional = false)
     
    @Column(name = "ASCENDENTE")
    private Boolean ascendente;
    @JoinColumn(name = "ID", referencedColumnName = "ID_ESTUDIO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Estudio estudio;

    public DatosEstudioUsuario() {
    }

    public DatosEstudioUsuario(Integer id) {
        this.id = id;
    }

    public DatosEstudioUsuario(Integer id, Boolean nombre, Boolean apellidos, Boolean ingresos, Boolean ascendente) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.ingresos = ingresos;
        this.ascendente = ascendente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getNombre() {
        return nombre;
    }

    public void setNombre(Boolean nombre) {
        this.nombre = nombre;
    }

    public Boolean getApellidos() {
        return apellidos;
    }

    public void setApellidos(Boolean apellidos) {
        this.apellidos = apellidos;
    }

    public Boolean getIngresos() {
        return ingresos;
    }

    public void setIngresos(Boolean ingresos) {
        this.ingresos = ingresos;
    }

    public Boolean getAscendente() {
        return ascendente;
    }

    public void setAscendente(Boolean ascendente) {
        this.ascendente = ascendente;
    }

    public Estudio getEstudio() {
        return estudio;
    }

    public void setEstudio(Estudio estudio) {
        this.estudio = estudio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatosEstudioUsuario)) {
            return false;
        }
        DatosEstudioUsuario other = (DatosEstudioUsuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "trabajoTAW.entity.DatosEstudioUsuario[ id=" + id + " ]";
    }
    

    
    
    
}
