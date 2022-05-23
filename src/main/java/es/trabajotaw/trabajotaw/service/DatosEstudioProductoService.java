package es.trabajotaw.trabajotaw.service;

import es.trabajotaw.trabajotaw.dao.DatosEstudioProductoRepository;
import es.trabajotaw.trabajotaw.dao.EstudioRepository;
import es.trabajotaw.trabajotaw.dto.DatosEstudioProductoDTO;
import es.trabajotaw.trabajotaw.entity.DatosEstudioProducto;
import es.trabajotaw.trabajotaw.entity.Estudio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatosEstudioProductoService {

    @Autowired
    private DatosEstudioProductoRepository estudioProductoRepository;
    @Autowired
    private EstudioRepository estudioRepository;

    public DatosEstudioProductoDTO getById(Integer idEstudio){
        DatosEstudioProducto estudioProducto = estudioProductoRepository.getById(idEstudio);
        return estudioProducto == null ? null : estudioProducto.toDTO();
    }

    public void delete(Integer idEstudio){
        DatosEstudioProducto estudioProducto = estudioProductoRepository.getById(idEstudio);
        estudioProductoRepository.delete(estudioProducto);
    }

    public void save(Boolean categorias,Boolean vendidos,Boolean promocion,
                       Double precioSalida,Double precioActual,String idEstudio){


        DatosEstudioProducto estudioProducto = new DatosEstudioProducto();

        estudioProducto = rellenarEstudioProducto(estudioProducto,categorias,
                vendidos,promocion,precioSalida,precioActual,idEstudio);

        estudioProductoRepository.save(estudioProducto);

    }

    public void save(String strIdEstudioProducto,Boolean categorias,
                     Boolean vendidos,Boolean promocion,Double precioSalida,
                     Double precioActual,String idEstudio){


        DatosEstudioProducto estudioProducto = estudioProductoRepository.getById(Integer.parseInt(strIdEstudioProducto));

        estudioProducto = rellenarEstudioProducto(estudioProducto,categorias,
                vendidos,promocion,precioSalida,precioActual,idEstudio);

        estudioProductoRepository.save(estudioProducto);

    }

    private DatosEstudioProducto rellenarEstudioProducto(
            DatosEstudioProducto estudioProducto, Boolean categorias,
            Boolean vendidos, Boolean promocion, Double precioSalida,
            Double precioActual, String idEstudio){
        Estudio estudio = this.estudioRepository.getById(Integer.parseInt(idEstudio));
        estudioProducto.setId(estudio.getIdEstudio());
        estudioProducto.setCategorias(categorias);
        estudioProducto.setVendidos(vendidos);
        estudioProducto.setPromocion(promocion);
        estudioProducto.setPrecioActual(precioActual);
        estudioProducto.setPrecioSalida(precioSalida);
        return estudioProducto;
    }



}
