
package com.br.lp3.model.daos;

import com.br.lp3.model.entities.ProfileAccount;
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
        ProfileAccount profile = em.createNamedQuery("ProfileAccount.findByIdAccount", ProfileAccount.class).setParameter("idAccount", id).getResultList().get(0);
        return profile;
    }

    @Override
    public List<ProfileAccount> readyAll() {
        List<ProfileAccount> books = em.createNamedQuery("ProfileAccount.findAll", ProfileAccount.class).getResultList();
        return books;
    }
    
}
