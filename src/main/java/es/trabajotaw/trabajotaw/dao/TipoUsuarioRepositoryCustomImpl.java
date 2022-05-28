package es.trabajotaw.trabajotaw.dao;

import es.trabajotaw.trabajotaw.entity.TipoUsuario;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class TipoUsuarioRepositoryCustomImpl implements TipoUsuarioRepositoryCustom{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<TipoUsuario> findByTipo(String filtroNombre) {
        Query q;
        q = em.createQuery("SELECT t FROM TipoUsuario t WHERE t.tipo = :tipo");
        q.setParameter("tipo", '%' + filtroNombre +'%');
        return q.getResultList();
    }
}
