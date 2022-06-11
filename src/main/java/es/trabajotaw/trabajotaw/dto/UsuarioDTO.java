    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.trabajotaw.trabajotaw.dto;


    import es.trabajotaw.trabajotaw.entity.Categoria;
    import org.springframework.format.annotation.DateTimeFormat;

    import javax.persistence.CascadeType;
    import javax.persistence.ManyToOne;
    import java.util.Date;
    import java.util.List;
    import java.util.Objects;

    /**
 *
 * @author nicor
 */
public class UsuarioDTO {
    private Integer idUsuario;
    private String nombreUsuario;
    private String contrasenya;
    private String email;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;
    private Character sexo;
    @ManyToOne(cascade = CascadeType.ALL)
    private DireccionDTO direccion;
    private TipoUsuarioDTO tipoUsuario;

    private List<Integer> categoriasFavoritas;

        public List<Categoria> getCategoriasFavoritasEntity() {
            return categoriasFavoritasEntity;
        }

        public void setCategoriasFavoritasEntity(List<Categoria> categoriasFavoritasEntity) {
            this.categoriasFavoritasEntity = categoriasFavoritasEntity;
        }

        private List<Categoria> categoriasFavoritasEntity;

    public List<Integer> getCategoriasFavoritas() {
        return categoriasFavoritas;
    }

    public void setCategoriasFavoritas(List<Integer> idCategoriasFavoritas) {
        this.categoriasFavoritas = idCategoriasFavoritas;
    }

    private List<Integer> listaUsuarioDTOList;
    private List<Integer> notificacionDTOList;

    public UsuarioDTO() {
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

    public DireccionDTO getDireccion() {
        return direccion;
    }

    public void setDireccion(DireccionDTO direccion) {
        this.direccion = direccion;
    }

    public TipoUsuarioDTO getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuarioDTO tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public List<Integer> getListaUsuarioDTOList(){
        return listaUsuarioDTOList;
    }

    public void setListaUsuarioDTOList (List<Integer> listaUsuarioDTOList){
        this.listaUsuarioDTOList = listaUsuarioDTOList;
    }

    public List<Integer> getNotificacionDTOList() {
        return notificacionDTOList;
    }

    public void setNotificacionDTOList(List<Integer> notificacionDTOList) {
        this.notificacionDTOList = notificacionDTOList;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.idUsuario);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UsuarioDTO other = (UsuarioDTO) obj;
        if (!Objects.equals(this.idUsuario, other.idUsuario)) {
            return false;
        }
        return true;
    }
    
    
}
