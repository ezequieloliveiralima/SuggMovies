
package com.br.lp3.model.daos;

import com.br.lp3.model.entities.ProfileAccount;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 *
 * @author Rodrigo T. L. Takeuti
 */
@Stateless(name = "profileAccount")
public class ProfileAccountDAO implements GenericDAO<ProfileAccount>{

    @PersistenceContext(unitName = "SuggMovies-ejbPU", type = PersistenceContextType.TRANSACTION)
    
    EntityManager em;
    
    @Override
    public void insert(ProfileAccount e) {
        em.persist(e);
    }

    @Override
    public void update(ProfileAccount e) {
        em.merge(e);
    }

    @Override
    public void remove(ProfileAccount e) {
        em.remove(e);
    }

    @Override
    public ProfileAccount readById(long id) {
        ProfileAccount book = em.find(ProfileAccount.class, id);
        return book;
    }

    @Override
    public List<ProfileAccount> readyAll() {
        List<ProfileAccount> books = em.createNamedQuery("ProfileAccount.findAll", ProfileAccount.class).getResultList();
        return books;
    }
    
}
