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
import es.trabajotaw.trabajotaw.entity.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository cr;
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
            //categorias = this.cr.getByNombre(filtroNombre);
        }

        return this.listaEntityADTO(categorias);
    }

    public CategoriaDTO buscarCategoria (Integer id) {
        Categoria categoria = this.cr.getById(id);
        return categoria.toDTO();
    }

    public void borrarCategoria (Integer id) {
        Categoria categoria = this.cr.getById(id);

        this.cr.delete(categoria);
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

    public void modificarCategoria (Integer id,
                              String nombreCategoria) {

        Categoria categoria = this.cr.getById(id);

        this.rellenarCategoria(categoria, nombreCategoria);

        this.cr.save(categoria);
    }
}
