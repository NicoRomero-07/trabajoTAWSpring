package es.trabajotaw.trabajotaw.service;

import es.trabajotaw.trabajotaw.dao.DatosEstudioProductoRepository;
import es.trabajotaw.trabajotaw.dao.DatosEstudioUsuarioRepository;
import es.trabajotaw.trabajotaw.dao.EstudioRepository;
import es.trabajotaw.trabajotaw.dao.UsuarioRepository;
import es.trabajotaw.trabajotaw.dto.EstudioDTO;
import es.trabajotaw.trabajotaw.entity.DatosEstudioProducto;
import es.trabajotaw.trabajotaw.entity.DatosEstudioUsuario;
import es.trabajotaw.trabajotaw.entity.Estudio;
import es.trabajotaw.trabajotaw.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EstudioService {

    @Autowired
    private EstudioRepository estudioRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private DatosEstudioProductoRepository estudioProductoRepository;
    @Autowired
    private DatosEstudioUsuarioRepository estudioUsuarioRepository;

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

    public EstudioDTO getById(Integer idEstudio){
        Estudio estudio = estudioRepository.getById(idEstudio);
        return estudio.toDTO();
    }

    public void remove(Integer idEstudio){
        Estudio estudio = this.estudioRepository.getById(idEstudio);
        estudioRepository.delete(estudio);
    }

    public EstudioDTO save(String nombre, String analista, String descripcion, String element, String idEstudioProducto, String idEstudioUsuario){
        Estudio estudio = new Estudio();
        estudio = rellenarEstudio(estudio,nombre,analista,descripcion,element,idEstudioProducto,idEstudioUsuario);
        estudioRepository.save(estudio);
        return estudio.toDTO();
    }

    public EstudioDTO save(String idEstudio,String nombre,String analista,String descripcion,String element,String idEstudioProducto,String idEstudioUsuario){
        Estudio estudio = this.estudioRepository.getById(Integer.parseInt(idEstudio));
        estudio = rellenarEstudio(estudio,nombre,analista,descripcion,element,idEstudioProducto,idEstudioUsuario);
        estudioRepository.save(estudio);
        return estudio.toDTO();
    }

    private Estudio rellenarEstudio(Estudio estudio,String nombre,String analista,String descripcion,String element,String idEstudioProducto,String idEstudioUsuario){
        if(nombre != null && !nombre.isEmpty()){
            estudio.setNombre(nombre);
        }
        if(analista != null && !analista.isEmpty()){
            Usuario user = this.usuarioRepository.getById(Integer.parseInt(analista));
            estudio.setAnalista(user);
        }

        if(descripcion != null && !descripcion.isEmpty()){
            estudio.setDescripcion(descripcion);
        }
        if(element != null && !element.isEmpty()){

            switch (element) {
                case "comprador":
                    estudio.setComprador(Boolean.TRUE);
                    estudio.setVendedor(Boolean.FALSE);
                    estudio.setProducto(Boolean.FALSE);
                    break;
                case "vendedor":
                    estudio.setComprador(Boolean.FALSE);
                    estudio.setVendedor(Boolean.TRUE);
                    estudio.setProducto(Boolean.FALSE);
                    break;
                default:
                    estudio.setComprador(Boolean.FALSE);
                    estudio.setVendedor(Boolean.FALSE);
                    estudio.setProducto(Boolean.TRUE);
                    break;
            }

        }
        if(idEstudioProducto != null && !idEstudioProducto.isEmpty() ){
            DatosEstudioProducto estudioProducto = this.estudioProductoRepository.getById(Integer.parseInt(idEstudioProducto));
            estudio.setDatosEstudioProducto(estudioProducto);
        }
        if(idEstudioUsuario != null && !idEstudioUsuario.isEmpty()){
            DatosEstudioUsuario estudioUsuario = this.estudioUsuarioRepository.getById(Integer.parseInt(idEstudioUsuario));
            estudio.setDatosEstudioUsuario(estudioUsuario);
        }
        return estudio;
    }

    public void copy(String str){

        Estudio estudio = estudioRepository.getById(Integer.parseInt(str));
        Estudio estudionew = new Estudio();

        estudionew.setAnalista(estudio.getAnalista());
        estudionew.setComprador(estudio.getComprador());
        estudionew.setDescripcion(estudio.getDescripcion());
        estudionew.setNombre(estudio.getNombre());
        estudionew.setProducto(estudio.getProducto());
        estudionew.setVendedor(estudio.getVendedor());

        estudioRepository.save(estudionew);

        Optional<DatosEstudioProducto> estudioProducto = this.estudioProductoRepository.findById(Integer.parseInt(str));
        Optional<DatosEstudioUsuario> estudioUsuario = this.estudioUsuarioRepository.findById(Integer.parseInt(str));

        if(estudioProducto.isPresent()){
            DatosEstudioProducto estudioProductonew = new DatosEstudioProducto();
            estudioProductonew.setCategorias(estudioProducto.get().getCategorias());
            estudioProductonew.setPrecioActual(estudioProducto.get().getPrecioActual());
            estudioProductonew.setPrecioSalida(estudioProducto.get().getPrecioSalida());
            estudioProductonew.setPromocion(estudioProducto.get().getPromocion());
            estudioProductonew.setVendidos(estudioProducto.get().getVendidos());

            estudioProductonew.setEstudio(estudionew);
            estudioProductonew.setId(estudionew.getIdEstudio());
            estudioProductoRepository.save(estudioProductonew);
            estudionew.setDatosEstudioProducto(estudioProductonew);

        }else if(estudioUsuario.isPresent()){
            DatosEstudioUsuario estudioUsuarionew = new DatosEstudioUsuario();

            estudioUsuarionew.setApellidos(estudioUsuario.get().getApellidos());
            estudioUsuarionew.setAscendente(estudioUsuario.get().getAscendente());
            estudioUsuarionew.setIngresos(estudioUsuario.get().getIngresos());
            estudioUsuarionew.setNombre(estudioUsuario.get().getNombre());

        }
        estudioRepository.save(estudionew);

    }
}
