/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.trabajotaw.trabajotaw.entity;


import es.trabajotaw.trabajotaw.dto.UsuarioDTO;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 *
 * @author nicol
 */
@Entity
@Table(name = "USUARIO")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario")
    , @NamedQuery(name = "Usuario.findByNombreUsuario", query = "SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario")
    , @NamedQuery(name = "Usuario.findByContrasenya", query = "SELECT u FROM Usuario u WHERE u.contrasenya = :contrasenya")
    , @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email")
    , @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "Usuario.findByPrimerApellido", query = "SELECT u FROM Usuario u WHERE u.primerApellido = :primerApellido")
    , @NamedQuery(name = "Usuario.findBySegundoApellido", query = "SELECT u FROM Usuario u WHERE u.segundoApellido = :segundoApellido")
    , @NamedQuery(name = "Usuario.findByFechaNacimiento", query = "SELECT u FROM Usuario u WHERE u.fechaNacimiento = :fechaNacimiento")
    , @NamedQuery(name = "Usuario.findBySexo", query = "SELECT u FROM Usuario u WHERE u.sexo = :sexo")})
public class Usuario {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_USUARIO")
    private Integer idUsuario;
    @Basic(optional = false)
    @Column(name = "NOMBRE_USUARIO")
    private String nombreUsuario;
    @Basic(optional = false)
    @Column(name = "CONTRASENYA")
    private String contrasenya;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "PRIMER_APELLIDO")
    private String primerApellido;
    @Column(name = "SEGUNDO_APELLIDO")
    private String segundoApellido;
    @Column(name = "FECHA_NACIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "SEXO")
    private Character sexo;
    @ManyToMany(mappedBy = "usuarioList")
    private List<ListaUsuario> listaUsuarioList;
    @ManyToMany(mappedBy = "usuarioList")
    private List<Categoria> categoriaList;
    @JoinTable(name = "USUARIO_NOTIFICACION", joinColumns = {
        @JoinColumn(name = "RECIBIDOR", referencedColumnName = "ID_USUARIO")}, inverseJoinColumns = {
        @JoinColumn(name = "NOTIFICACION", referencedColumnName = "ID_NOTIFICACION")})
    @ManyToMany
    private List<Notificacion> notificacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario1")
    private List<ListaProducto> listaProductoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comprador")
    private List<Puja> pujaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "notificante")
    private List<Notificacion> notificacionList1;
    @JoinColumn(name = "DIRECCION", referencedColumnName = "ID_DIRECCION")
    @ManyToOne(optional = false)
    private Direccion direccion;
    @JoinColumn(name = "TIPO_USUARIO", referencedColumnName = "ID_TIPO_USUARIO")
    @ManyToOne(optional = false)
    private TipoUsuario tipoUsuario;

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(Integer idUsuario, String nombreUsuario, String contrasenya, String email, String nombre, String primerApellido) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contrasenya = contrasenya;
        this.email = email;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

     
    public List<ListaUsuario> getListaUsuarioList() {
        return listaUsuarioList;
    }

    public void setListaUsuarioList(List<ListaUsuario> listaUsuarioList) {
        this.listaUsuarioList = listaUsuarioList;
    }

     
    public List<Categoria> getCategoriaList() {
        return categoriaList;
    }

    public void setCategoriaList(List<Categoria> categoriaList) {
        this.categoriaList = categoriaList;
    }

     
    public List<Notificacion> getNotificacionList() {
        return notificacionList;
    }

    public void setNotificacionList(List<Notificacion> notificacionList) {
        this.notificacionList = notificacionList;
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

     
    public List<Notificacion> getNotificacionList1() {
        return notificacionList1;
    }

    public void setNotificacionList1(List<Notificacion> notificacionList1) {
        this.notificacionList1 = notificacionList1;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.trabajotaw.entity.Usuario[ idUsuario=" + idUsuario + " ]";
    }

    public void setCategoriaFavorita(Categoria c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public UsuarioDTO toDTO() {
        UsuarioDTO dto = new UsuarioDTO();

        dto.setIdUsuario(idUsuario);
        dto.setNombreUsuario(nombreUsuario);
        dto.setContrasenya(contrasenya);
        dto.setNombre(nombre);
        dto.setPrimerApellido(primerApellido);
        dto.setSegundoApellido(segundoApellido);
        dto.setEmail(email);
        dto.setDireccion(direccion.toDTO());
        dto.setSexo(sexo);
        dto.setTipoUsuario(tipoUsuario.toDTO());
        dto.setFechaNacimiento(fechaNacimiento);

        return dto;
    }

    public Usuario (UsuarioDTO dto){
        this.setIdUsuario(dto.getIdUsuario());
        this.setNombreUsuario(dto.getNombreUsuario());
        this.setContrasenya(dto.getContrasenya());
        this.setNombre(dto.getNombre());
        this.setPrimerApellido(dto.getPrimerApellido());
        this.setSegundoApellido(dto.getSegundoApellido());
        this.setEmail(dto.getEmail());
        this.setDireccion(new Direccion(dto.getDireccion()));
        this.setSexo(dto.getSexo());
        this.setTipoUsuario(new TipoUsuario(dto.getTipoUsuario()));
        this.setFechaNacimiento(dto.getFechaNacimiento());
    }

}
