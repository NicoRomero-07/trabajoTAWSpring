package es.trabajotaw.trabajotaw.service;

import es.trabajotaw.trabajotaw.dao.DireccionRepository;
import es.trabajotaw.trabajotaw.dto.DireccionDTO;
import es.trabajotaw.trabajotaw.dto.UsuarioDTO;
import es.trabajotaw.trabajotaw.entity.Direccion;
import es.trabajotaw.trabajotaw.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nicor
 */
@Service
public class DireccionService {
    @Autowired
    DireccionRepository dr;

    public DireccionDTO guardarDireccion(DireccionDTO direccion) {
        Direccion direccionEntity = new Direccion(direccion);
        dr.save(direccionEntity);
        return direccionEntity.toDTO();
    }

    public void modificarDireccion(DireccionDTO direccion) {
        Direccion direccionEntity = new Direccion(direccion);
        dr.save(direccionEntity);
    }

    public void borrarUsuarioDireccion(Integer id) {
        Direccion direccion = this.dr.findById(id).orElse(null);
        direccion.setUsuarioList(new ArrayList<>());
        this.dr.save(direccion);
    }

    public void borrarDireccion(Integer idDireccion) {
        Direccion direccion = this.dr.findById(idDireccion).orElse(null);
        this.dr.delete(direccion);
    }

    /*
    @EJB DireccionFacade df;
    
    private List<DireccionDTO> listaEntityADTO (List<Direccion> lista) {
        List<DireccionDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Direccion direccion:lista) {
                listaDTO.add(direccion.toDTO());
            }
        }
        return listaDTO;
    }
    
    public List<DireccionDTO> listarDirecciones (String filtroNombre) {
        List<Direccion> direcciones = null;

        if (filtroNombre == null || filtroNombre.isEmpty()) {
            direcciones = this.df.findAll();        
        } else {
            direcciones = this.df.findByNombreDireccion(filtroNombre);
        }
        
        return this.listaEntityADTO(direcciones);                
    } 
    
    public DireccionDTO buscarDireccion (Integer id) {
        Direccion direccion = this.df.find(id);
        return direccion.toDTO();
    }
    
    public void borrarDireccion (Integer id) {
        Direccion direccion = this.df.find(id);

        this.df.remove(direccion);        
    }
    
    private void rellenarDireccion (Direccion direccion, String tipo, String calle, int numero, int codigoPostal, int planta, String puerta ) {
        
        direccion.setTipo(tipo);
        direccion.setCalle(calle);
        direccion.setNumero(numero);
        direccion.setCodigoPostal(codigoPostal);
        direccion.setPlanta(planta);
        direccion.setPuerta(puerta);
        direccion.setUsuarioList(new ArrayList<>());
                       
    }
    
    public void crearDireccion (String tipo, String calle, int numero, int codigoPostal, int planta, String puerta) {
        Direccion direccion = new Direccion();

        this.rellenarDireccion(direccion, tipo, calle, numero, codigoPostal, planta, puerta);

        this.df.create(direccion);
    }

    public void modificarDireccion (Integer id, String tipo, String calle, int numero, int codigoPostal, int planta, String puerta) {
        
        Direccion direccion = this.df.find(id);

        this.rellenarDireccion(direccion, tipo, calle, numero, codigoPostal, planta, puerta);

        this.df.edit(direccion);
    }

    public Direccion findDireccion(String calle, String numero) {
        Direccion direccion = (Direccion) df.findByCalleNumero(calle,Integer.parseInt(numero));
        return direccion;
    }
    */
    
}
