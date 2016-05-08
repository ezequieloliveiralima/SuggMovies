
package com.br.lp3.model.daos;

import com.br.lp3.model.entities.Indication;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 *
 * @author Rodrigo T. L. Takeuti
 */
@LocalBean
@Stateless
public class IndicationDAO implements GenericDAO<Indication>{

    @PersistenceContext(unitName = "SuggMovies-ejbPU", type = PersistenceContextType.TRANSACTION)
    
    EntityManager em;
    
    @Override
    public void insert(Indication e) {
        em.persist(e);
    }

    @Override
    public void update(Indication e) {
        em.merge(e);
    }

    @Override
    public void remove(Indication e) {
        em.remove(e);
    }

    @Override
    public Indication readById(long id) {
        Indication book = em.find(Indication.class, id);
        return book;
    }

    @Override
    public List<Indication> readyAll() {
        List<Indication> books = em.createNamedQuery("Indication.findAll", Indication.class).getResultList();
        return books;
    }
    
}
