package es.trabajotaw.trabajotaw.service;

import es.trabajotaw.trabajotaw.dao.EstudioRepository;
import es.trabajotaw.trabajotaw.dto.EstudioDTO;
import es.trabajotaw.trabajotaw.entity.Estudio;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstudioService {
    private EstudioRepository estudioRepository;

    public List<EstudioDTO> listarClientes (String filtroNombre) {
        List<Estudio> estudios;

        if (filtroNombre == null || filtroNombre.isEmpty()) {
            estudios = this.estudioRepository.findAll();
        } else {
            estudios = this.estudioRepository.findByNombreContaining(filtroNombre);
        }

        return this.listaEntityADTO(estudios);
    }

    private List<EstudioDTO> listaEntityADTO(List<Estudio> lista) {
        List<EstudioDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Estudio estudio:lista) {
                listaDTO.add(estudio.toDTO());
            }
        }
        return listaDTO;
    }
}
