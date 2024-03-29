/**
 *
 * @author Nicolás Zhao(100%)
 */
package es.trabajotaw.trabajotaw.entity;

import es.trabajotaw.trabajotaw.dto.NotificacionDTO;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "NOTIFICACION")
 
@NamedQueries({
    @NamedQuery(name = "Notificacion.findAll", query = "SELECT n FROM Notificacion n")
    , @NamedQuery(name = "Notificacion.findByIdNotificacion", query = "SELECT n FROM Notificacion n WHERE n.idNotificacion = :idNotificacion")
    , @NamedQuery(name = "Notificacion.findByContenido", query = "SELECT n FROM Notificacion n WHERE n.contenido = :contenido")
    , @NamedQuery(name = "Notificacion.findByFechaEnvio", query = "SELECT n FROM Notificacion n WHERE n.fechaEnvio = :fechaEnvio")})
public class Notificacion   {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_NOTIFICACION")
    private Integer idNotificacion;
    @Basic(optional = false)
    @Column(name = "CONTENIDO")
    private String contenido;
    @Basic(optional = false)
     
    @Column(name = "FECHA_ENVIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEnvio;
    @ManyToMany(mappedBy = "notificacionList")
    private List<Usuario> usuarioList;
    @JoinColumn(name = "NOTIFICANTE", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private Usuario notificante;

    public Notificacion() {
    }

    public Notificacion(Integer idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public Notificacion(Integer idNotificacion, String contenido, Date fechaEnvio) {
        this.idNotificacion = idNotificacion;
        this.contenido = contenido;
        this.fechaEnvio = fechaEnvio;
    }

    public Integer getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(Integer idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

     
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public Usuario getNotificante() {
        return notificante;
    }

    public void setNotificante(Usuario notificante) {
        this.notificante = notificante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNotificacion != null ? idNotificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notificacion)) {
            return false;
        }
        Notificacion other = (Notificacion) object;
        if ((this.idNotificacion == null && other.idNotificacion != null) || (this.idNotificacion != null && !this.idNotificacion.equals(other.idNotificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.trabajotaw.entity.Notificacion[ idNotificacion=" + idNotificacion + " ]";
    }

    public NotificacionDTO toDTO(){
        NotificacionDTO dto = new NotificacionDTO();
        dto.setIdNotificacion(idNotificacion);
        dto.setFechaEnvio(fechaEnvio);
        dto.setContenido(contenido);
        dto.setNotificante(notificante.getIdUsuario());
        List<Integer> notificados = null;
        if (usuarioList!= null && !usuarioList.isEmpty()){
            notificados = new ArrayList<>();
            for (Usuario usuario: usuarioList)
            notificados.add(usuario.getIdUsuario());
        }
        dto.setUsuarioDTOList(notificados);
        return dto;
    }

    public Notificacion(NotificacionDTO dto, Usuario notificante, List<Usuario> notificados){
        this.setIdNotificacion(dto.getIdNotificacion());
        this.setNotificante(notificante);
        this.setContenido(dto.getContenido());
        this.setFechaEnvio(dto.getFechaEnvio());
        this.setUsuarioList(notificados);
    }
}
