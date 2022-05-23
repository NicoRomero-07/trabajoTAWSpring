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
public class DatosEstudioUsuarioDTO {
    private Integer id;
    private Boolean nombre;
    private Boolean apellidos;
    private Boolean ingresos;
    private Boolean ascendente;
    private EstudioDTO estudio;
    
    public DatosEstudioUsuarioDTO() {
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

    public EstudioDTO getEstudio() {
        return estudio;
    }

    public void setEstudio(EstudioDTO estudio) {
        this.estudio = estudio;
    }
    
}
