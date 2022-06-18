package es.trabajotaw.trabajotaw.service;

import es.trabajotaw.trabajotaw.dao.ProductoRepository;
import es.trabajotaw.trabajotaw.dao.PujaRepository;
import es.trabajotaw.trabajotaw.dao.UsuarioRepository;
import es.trabajotaw.trabajotaw.dto.PujaDTO;
import es.trabajotaw.trabajotaw.dto.UsuarioDTO;
import es.trabajotaw.trabajotaw.entity.Producto;
import es.trabajotaw.trabajotaw.entity.Puja;
import es.trabajotaw.trabajotaw.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PujaService {

    @Autowired
    private PujaRepository pujaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private List<PujaDTO> listaEntityADTO (List<Puja> lista) {
        List<PujaDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Puja puja:lista) {
                listaDTO.add(puja.toDTO());
            }
        }
        return listaDTO;
    }

    public List<PujaDTO> buscarPujasProducto(Integer idProducto){
        Producto p = productoRepository.getById(idProducto);
        List<Puja> pujas = pujaRepository.findByProducto(p);
        return listaEntityADTO(pujas);
    }

    public void rellenarPuja(Puja p, Integer idComprador, Integer idProducto, double cantidad){

        Usuario comprador = usuarioRepository.getById(idComprador);
        Producto producto = productoRepository.getById(idProducto);

        p.setComprador(comprador);
        p.setProducto(producto);
        p.setCantidad(cantidad);
    }

    public void guardarPuja(Integer idComprador, Integer idProducto, double cantidad){
        Puja p = new Puja();

        rellenarPuja(p,idComprador,idProducto,cantidad);

        pujaRepository.save(p);
    }
}
