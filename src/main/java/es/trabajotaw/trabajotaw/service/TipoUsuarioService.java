/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.trabajotaw.trabajotaw.service;

import es.trabajotaw.trabajotaw.dao.TipoUsuarioRepository;
import es.trabajotaw.trabajotaw.dao.TipoUsuarioRepositoryCustom;
import es.trabajotaw.trabajotaw.dao.TipoUsuarioRepositoryCustomImpl;
import es.trabajotaw.trabajotaw.dto.TipoUsuarioDTO;
import es.trabajotaw.trabajotaw.entity.TipoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nicor
 */
@Service
public class TipoUsuarioService {
    
    @Autowired
    TipoUsuarioRepository tur;
    @Autowired
    TipoUsuarioRepositoryCustom turcI;
    
    private List<TipoUsuarioDTO> listaEntityADTO (List<TipoUsuario> lista) {
        List<TipoUsuarioDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (TipoUsuario tipoUsuario:lista) {
                listaDTO.add(tipoUsuario.toDTO());
            }
        }
        return listaDTO;
    }
    
    public List<TipoUsuarioDTO> listarTipoUsuarios (String filtroNombre) {
        List<TipoUsuario> tipoUsuarios = null;

        if (filtroNombre == null || filtroNombre.isEmpty()) {
            tipoUsuarios = this.tur.findAll();        
        } else {
            tipoUsuarios = this.turcI.findByTipo(filtroNombre);
        }
        
        return this.listaEntityADTO(tipoUsuarios);                
    } 
    
    public TipoUsuarioDTO buscarTipoUsuario (Integer id) {
        TipoUsuario tipoUsuario = this.tur.getById(id);
        return tipoUsuario.toDTO();
    }
    
    public void borrarTipoUsuario (Integer id) {
        TipoUsuario tipoUsuario = this.tur.getById(id);

        this.tur.delete(tipoUsuario);
    }
    
    private void rellenarTipoUsuario (TipoUsuario tipoUsuario,
                              String nombreTipoUsuario) {
        
        tipoUsuario.setTipo(nombreTipoUsuario);
        
                       
    }
    
    public void crearTipoUsuario (String nombreTipoUsuario) {
        TipoUsuario tipoUsuario = new TipoUsuario();

        this.rellenarTipoUsuario(tipoUsuario, nombreTipoUsuario);

        this.tur.save(tipoUsuario);
    }

    public void modificarTipoUsuario (Integer id,
                              String nombreTipoUsuario) {
        
        TipoUsuario tipoUsuario = this.tur.getById(id);

        this.rellenarTipoUsuario(tipoUsuario, nombreTipoUsuario);

        this.tur.save(tipoUsuario);
    }


}
