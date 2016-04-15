
package com.br.lp3.model.daos;

import com.br.lp3.model.entities.Account;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 *
 * @author Rodrigo T. L. Takeuti
 */
@Stateless(name = "account")
public class AccountDAO implements GenericDAO<Account>{

    @PersistenceContext(unitName = "SuggMovies-ejbPU", type = PersistenceContextType.TRANSACTION)
    
    EntityManager em;
    
    @Override
    public void insert(Account e) {
        em.persist(e);
    }

    @Override
    public void update(Account e) {
        em.merge(e);
    }

    @Override
    public void remove(Account e) {
        em.remove(e);
    }

    @Override
    public Account readById(long id) {
        Account movie = em.find(Account.class, id);
        return movie;
    }

    @Override
    public List<Account> readyAll() {
        List<Account> movies = em.createNamedQuery("Account.findAll", Account.class).getResultList();
        return movies;
    }
    
}
