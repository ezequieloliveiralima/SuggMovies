
package com.br.lp3.model.daos;

import com.br.lp3.model.entities.CategoryBook;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 *
 * @author Rodrigo T. L. Takeuti
 */
@Stateless(name = "categoryBook")
public class CategoryBookDAO implements GenericDAO<CategoryBook>{

    @PersistenceContext(unitName = "SuggMovies-ejbPU", type = PersistenceContextType.TRANSACTION)
    
    EntityManager em;
    
    @Override
    public void insert(CategoryBook e) {
        em.persist(e);
    }

    @Override
    public void update(CategoryBook e) {
        em.merge(e);
    }

    @Override
    public void remove(CategoryBook e) {
        em.remove(e);
    }

    @Override
    public CategoryBook readById(long id) {
        CategoryBook book = em.find(CategoryBook.class, id);
        return book;
    }

    @Override
    public List<CategoryBook> readyAll() {
        List<CategoryBook> books = em.createNamedQuery("CategoryBook.findAll", CategoryBook.class).getResultList();
        return books;
    }
    
}
