package es.trabajotaw.trabajotaw.service;

import es.trabajotaw.trabajotaw.dao.DatosEstudioUsuarioRepository;
import es.trabajotaw.trabajotaw.dao.EstudioRepository;
import es.trabajotaw.trabajotaw.dto.DatosEstudioUsuarioDTO;
import es.trabajotaw.trabajotaw.entity.DatosEstudioUsuario;
import es.trabajotaw.trabajotaw.entity.Estudio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DatosEstudioUsuarioService{

    @Autowired
    private DatosEstudioUsuarioRepository estudioUsuarioRepository;
    @Autowired
    private EstudioRepository estudioRepository;

    public DatosEstudioUsuarioDTO findById(Integer idEstudio){
        Optional<DatosEstudioUsuario> estudioUsuario = estudioUsuarioRepository.findById(idEstudio);
        return estudioUsuario.isPresent() ? estudioUsuario.get().toDTO() : new DatosEstudioUsuarioDTO();
    }

    public void delete(Integer idEstudio){
        DatosEstudioUsuario estudioUsuario = estudioUsuarioRepository.getById(idEstudio);
        estudioUsuarioRepository.delete(estudioUsuario);
    }

    public void save(Boolean nombre,Boolean apellidos,Boolean ingresos,
                       Boolean ascendente,String idEstudio){


        DatosEstudioUsuario estudioUsuario = new DatosEstudioUsuario();

        estudioUsuario = rellenarEstudioUsuario(estudioUsuario,
                nombre,apellidos,ingresos,ascendente,idEstudio);

        estudioUsuarioRepository.save(estudioUsuario);

    }

    public void save(String strIdEstudioUsuario,Boolean nombre,Boolean apellidos,
                     Boolean ingresos,Boolean ascendente,String idEstudio){


        DatosEstudioUsuario estudioUsuario = estudioUsuarioRepository.getById(Integer.parseInt(strIdEstudioUsuario));

        estudioUsuario = rellenarEstudioUsuario(estudioUsuario,
                nombre,apellidos,ingresos,ascendente,idEstudio);

        estudioUsuarioRepository.save(estudioUsuario);

    }

    private DatosEstudioUsuario rellenarEstudioUsuario(
            DatosEstudioUsuario estudioUsuario,Boolean nombre,
            Boolean apellidos,Boolean ingresos,Boolean ascendente,String idEstudio){

        Estudio estudio = this.estudioRepository.getById(Integer.parseInt(idEstudio));
        estudioUsuario.setId(estudio.getIdEstudio());
        estudioUsuario.setNombre(nombre);
        estudioUsuario.setApellidos(apellidos);
        estudioUsuario.setIngresos(ingresos);
        estudioUsuario.setAscendente(ascendente);

        return estudioUsuario;
    }
}
