/**
 *
 * @author Nicolás Zhao(100%)
 */
package es.trabajotaw.trabajotaw.entity;

import es.trabajotaw.trabajotaw.dto.ListaUsuarioDTO;
import es.trabajotaw.trabajotaw.dto.UsuarioDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "LISTA_USUARIO")
 
@NamedQueries({
    @NamedQuery(name = "ListaUsuario.findAll", query = "SELECT l FROM ListaUsuario l")
    , @NamedQuery(name = "ListaUsuario.findByIdListaUsuario", query = "SELECT l FROM ListaUsuario l WHERE l.idListaUsuario = :idListaUsuario")
    , @NamedQuery(name = "ListaUsuario.findByNombre", query = "SELECT l FROM ListaUsuario l WHERE l.nombre = :nombre")})
public class ListaUsuario   {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_LISTA_USUARIO")
    private Integer idListaUsuario;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @JoinTable(name = "USUARIO_LISTA_USUARIO", joinColumns = {
        @JoinColumn(name = "LISTA", referencedColumnName = "ID_LISTA_USUARIO")}, inverseJoinColumns = {
        @JoinColumn(name = "USUARIO", referencedColumnName = "ID_USUARIO")})
    @ManyToMany
    private List<Usuario> usuarioList;

    public ListaUsuario() {
    }

    public ListaUsuario(Integer idListaUsuario) {
        this.idListaUsuario = idListaUsuario;
    }

    public ListaUsuario(Integer idListaUsuario, String nombre) {
        this.idListaUsuario = idListaUsuario;
        this.nombre = nombre;
    }

    public Integer getIdListaUsuario() {
        return idListaUsuario;
    }

    public void setIdListaUsuario(Integer idListaUsuario) {
        this.idListaUsuario = idListaUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        hash += (idListaUsuario != null ? idListaUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ListaUsuario)) {
            return false;
        }
        ListaUsuario other = (ListaUsuario) object;
        if ((this.idListaUsuario == null && other.idListaUsuario != null) || (this.idListaUsuario != null && !this.idListaUsuario.equals(other.idListaUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.trabajotaw.entity.ListaUsuario[ idListaUsuario=" + idListaUsuario + " ]";
    }

    public ListaUsuarioDTO toDTO() {

        ListaUsuarioDTO dto = new ListaUsuarioDTO();

        dto.setIdListaUsuario(this.idListaUsuario);
        dto.setNombre(this.nombre);
        /*
        List<UsuarioDTO> listaDTO = null;
        if (this.usuarioList != null) {
            listaDTO = new ArrayList<>();
            for (Usuario usuario:this.usuarioList) {
                listaDTO.add(usuario.toDTO());
            }
        }
        dto.setUsuarioDTOList(listaDTO);
        */
        return dto;
    }

    public ListaUsuario(ListaUsuarioDTO dto){
        this.setIdListaUsuario(dto.getIdListaUsuario());
        this.setNombre(dto.getNombre());

        List<Usuario> usuarioList = null;
        if (dto.getUsuarioDTOList() != null){
            usuarioList = new ArrayList<>();
            for (UsuarioDTO udto :dto.getUsuarioDTOList()) {
                usuarioList.add(new Usuario(udto));
            }
        }
        this.setUsuarioList(usuarioList);
    }
}
