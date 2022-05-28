/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.trabajotaw.trabajotaw.entity;

import es.trabajotaw.trabajotaw.dto.CategoriaDTO;

import javax.persistence.*;
import java.util.List;

/**
 *
 * @author nicol
 */
@Entity
@Table(name = "CATEGORIA")
 
@NamedQueries({
    @NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c")
    , @NamedQuery(name = "Categoria.findByIdCategoria", query = "SELECT c FROM Categoria c WHERE c.idCategoria = :idCategoria")
    , @NamedQuery(name = "Categoria.findByNombre", query = "SELECT c FROM Categoria c WHERE c.nombre = :nombre")})
public class Categoria   {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CATEGORIA")
    private Integer idCategoria;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @JoinTable(name = "CATEGORIA_PRODUCTO", joinColumns = {
        @JoinColumn(name = "CATEGORIA", referencedColumnName = "ID_CATEGORIA")}, inverseJoinColumns = {
        @JoinColumn(name = "PRODUCTO", referencedColumnName = "ID_PRODUCTO")})
    @ManyToMany
    private List<Producto> productoList;
    @JoinTable(name = "CATEGORIAS_FAVORITAS", joinColumns = {
        @JoinColumn(name = "CATEGORIA", referencedColumnName = "ID_CATEGORIA")}, inverseJoinColumns = {
        @JoinColumn(name = "USUARIO", referencedColumnName = "ID_USUARIO")})
    @ManyToMany
    private List<Usuario> usuarioList;

    public Categoria() {
    }

    public Categoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Categoria(Integer idCategoria, String nombre) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

     
    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

     
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategoria != null ? idCategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        if ((this.idCategoria == null && other.idCategoria != null) || (this.idCategoria != null && !this.idCategoria.equals(other.idCategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.trabajotaw.entity.Categoria[ idCategoria=" + idCategoria + " ]";
    }

    public CategoriaDTO toDTO() {
        CategoriaDTO categoriaDTO = new CategoriaDTO();

        categoriaDTO.setIdCategoria(idCategoria);
        categoriaDTO.setNombre(nombre);

        return categoriaDTO;
    }
    
}
