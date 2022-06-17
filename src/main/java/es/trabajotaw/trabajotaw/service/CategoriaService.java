/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

/**
 *
 * @author nicor
 */
package es.trabajotaw.trabajotaw.service;

import es.trabajotaw.trabajotaw.dao.CategoriaRepository;
import es.trabajotaw.trabajotaw.dto.CategoriaDTO;
import es.trabajotaw.trabajotaw.dto.UsuarioDTO;
import es.trabajotaw.trabajotaw.entity.Categoria;
import es.trabajotaw.trabajotaw.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository cr;
    @Autowired
    UsuarioService us;

    private List<CategoriaDTO> listaEntityADTO (List<Categoria> lista) {
        List<CategoriaDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Categoria categoria:lista) {
                listaDTO.add(categoria.toDTO());
            }
        }
        return listaDTO;
    }

    public List<CategoriaDTO> listarCategorias (String filtroNombre) {
        List<Categoria> categorias = null;

        if (filtroNombre == null || filtroNombre.isEmpty()) {
            categorias = this.cr.findAll();
        } else {
            categorias = this.cr.findByNombreCategoria('%'+filtroNombre+'%');
        }

        return this.listaEntityADTO(categorias);
    }

    public CategoriaDTO buscarCategoria (Integer id) {
        Categoria categoria = this.cr.getById(id);
        return categoria.toDTO();
    }

    public void borrarCategoria (Integer id) {
        Categoria categoria = this.cr.findById(id).orElse(null);

        this.cr.delete(categoria);
    }

    public List<Categoria> getCategoriaListFromId(List<Integer> ids){
        List<Categoria> categoriaList = new ArrayList<>();
        for (Integer id : ids){
            categoriaList.add(this.cr.findById(id).orElse(null));
        }
        return categoriaList;
    }


    private void rellenarCategoria (Categoria categoria,
                              String nombreCategoria) {

        categoria.setNombre(nombreCategoria);
    }

    public void crearCategoria (String nombreCategoria) {
        Categoria categoria = new Categoria();

        this.rellenarCategoria(categoria, nombreCategoria);

        this.cr.save(categoria);
    }

    public void modificarCategoria (Categoria categoria) {

        this.cr.save(categoria);
    }

    public void guardarCategorias(List<Categoria> categoriasFavoritasEntity, UsuarioDTO usuario) {

        for(Categoria c : categoriasFavoritasEntity){
            List<Usuario> usuarios =  c.getUsuarioList();

            UsuarioDTO usuarioDTO = us.buscarUsuario(usuario.getIdUsuario());
            Usuario usuarioEntity = new Usuario(usuarioDTO);
            if(!c.getUsuarioList().contains(usuarioEntity)){
                usuarios.add(new Usuario(usuarioDTO));
                c.setUsuarioList(usuarios);
            }

            cr.save(c);
        }
    }

    public void eliminarUsuarioCategoria(UsuarioDTO usuarioDTO) {
        for(Categoria c: usuarioDTO.getCategoriasFavoritasEntity()){
            List<Usuario> usuarios = c.getUsuarioList();
            usuarios.remove(new Usuario(usuarioDTO));
            c.setUsuarioList(usuarios);
            cr.save(c);
        }
    }
}
