
package com.br.lp3.model.daos;

import com.br.lp3.exceptions.SigninEmailException;
import com.br.lp3.model.entities.Account;
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
        Account account = em.find(Account.class, id);
        return account;
    }
    
    public Account readyByEmail(String email) throws SigninEmailException
    {
        List<Account> accounts = em.createNamedQuery("Account.findByEmail", Account.class).setParameter("email", email).getResultList();
        if (accounts.size() != 1) {
            throw new SigninEmailException();
        }
        return accounts.get(0);
    }

    @Override
    public List<Account> readyAll() {
        List<Account> accounts = em.createNamedQuery("Account.findAll", Account.class).getResultList();
        return accounts;
    }
    
}
