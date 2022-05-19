/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.trabajotaw.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author nicol
 */
@Embeddable
public class ListaProductoPK implements Serializable {

    private static final long serialVersionUID = 4317021001719067844L;
    @Basic(optional = false)
     
    @Column(name = "PRODUCTO")
    private int producto;
    @Basic(optional = false)
     
    @Column(name = "USUARIO")
    private int usuario;

    public ListaProductoPK() {
    }

    public ListaProductoPK(int producto, int usuario) {
        this.producto = producto;
        this.usuario = usuario;
    }

    public int getProducto() {
        return producto;
    }

    public void setProducto(int producto) {
        this.producto = producto;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) producto;
        hash += (int) usuario;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ListaProductoPK)) {
            return false;
        }
        ListaProductoPK other = (ListaProductoPK) object;
        if (this.producto != other.producto) {
            return false;
        }
        if (this.usuario != other.usuario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.trabajotaw.entity.ListaProductoPK[ producto=" + producto + ", usuario=" + usuario + " ]";
    }
    
}
