/**
 *
 * @author Nicolás Zhao(100%)
 */
package es.trabajotaw.trabajotaw.service;

import java.util.ArrayList;
import java.util.List;

import es.trabajotaw.trabajotaw.dao.ListaUsuarioRepository;
import es.trabajotaw.trabajotaw.dao.UsuarioRepository;
import es.trabajotaw.trabajotaw.dto.ListaUsuarioDTO;
import es.trabajotaw.trabajotaw.dto.UsuarioDTO;
import es.trabajotaw.trabajotaw.entity.ListaUsuario;
import es.trabajotaw.trabajotaw.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ListaUsuarioService {
    @Autowired
    ListaUsuarioRepository listaUsuarioRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    public List<ListaUsuarioDTO> listaEntityADTO(List<ListaUsuario> lista) {
        List<ListaUsuarioDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (ListaUsuario lu:lista) {
                listaDTO.add(lu.toDTO());
            }
        }
        return listaDTO;
    }
    public List<ListaUsuarioDTO> listarListas (String filtroNombre) {
        List<ListaUsuario> listas = null;

        if (filtroNombre == null || filtroNombre.isEmpty()) {
            listas = this.listaUsuarioRepository.findAll();        
        } else {
            listas = this.listaUsuarioRepository.findByNombreContainsIgnoreCase('%'+filtroNombre+'%');
        }
        
        return this.listaEntityADTO(listas);                
    } 
    
    public ListaUsuarioDTO buscarLista (Integer id) {
        ListaUsuario lista = this.listaUsuarioRepository.findById(id).orElse(new ListaUsuario());
        return lista.toDTO();
    }
    
    public void borrarLista (Integer id) {
        this.listaUsuarioRepository.deleteById(id);
    }

    public void guardarLista (ListaUsuarioDTO listaUsuarioDTO){
        List<Usuario> usuarioList = null;
        if (listaUsuarioDTO.getUsuarioDTOList() != null && !listaUsuarioDTO.getUsuarioDTOList().isEmpty()){
            usuarioList = new ArrayList<>();
            for(Integer idUsuario : listaUsuarioDTO.getUsuarioDTOList()){
                usuarioList.add(this.usuarioRepository.findById(idUsuario).orElse(null));
            }
        }
        ListaUsuario listaUsuario = new ListaUsuario(listaUsuarioDTO, usuarioList);
        listaUsuarioRepository.save(listaUsuario);
    }


    
    private void rellenarLista (ListaUsuario lista,
                              String nombre, String[] listas) {
        
        lista.setNombre(nombre);
        List<Usuario> listaUsuarios = new ArrayList<>();
        for (String i : listas){
            Usuario l = this.usuarioRepository.findById(Integer.parseInt(i)).orElse(null);
            listaUsuarios.add(l);
        }
        lista.setUsuarioList(listaUsuarios);               
    }
    
    public ListaUsuarioDTO crearLista (String nombre,String[] listas) {
        ListaUsuario lista = new ListaUsuario();

        this.rellenarLista(lista, nombre,listas);

        this.listaUsuarioRepository.save(lista);
        return lista.toDTO();
    }

    
    public void modificarLista(Integer id, String nombre, String[] listas){
        ListaUsuario lista = this.listaUsuarioRepository.findById(id).orElse(null);
        
        this.rellenarLista(lista, nombre,listas);
        
        this.listaUsuarioRepository.save(lista);
    }
    
    public List<UsuarioDTO> usuariosRelacionados(Integer id){
        ListaUsuario listaUsuario = this.listaUsuarioRepository.findById(id).orElse(new ListaUsuario());
        List<UsuarioDTO> usuariosDTO = new ArrayList();
        List<Usuario> usuarios = listaUsuario.getUsuarioList();
        
        for(Usuario c : usuarios){
            usuariosDTO.add(c.toDTO());
        }
        return usuariosDTO;
    }
    
}
