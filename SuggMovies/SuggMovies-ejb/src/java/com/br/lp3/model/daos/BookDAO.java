
package com.br.lp3.model.daos;

import com.br.lp3.model.entities.Book;
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
public class BookDAO implements GenericDAO<Book>{

    @PersistenceContext(unitName = "SuggMovies-ejbPU", type = PersistenceContextType.TRANSACTION)
    
    EntityManager em;
    
    @Override
    public void insert(Book e) {
        em.persist(e);
    }

    @Override
    public void update(Book e) {
        em.merge(e);
    }

    @Override
    public void remove(Book e) {
        em.remove(e);
    }

    @Override
    public Book readById(long id) {
        Book book = em.find(Book.class, id);
        return book;
    }
    
    public Book readByIsbn(String isbn, long idAccount) {
        Book b = null;
        List<Book> books = em.createNamedQuery("Book.findByIsbn", Book.class).setParameter("isbn", isbn).getResultList();
        for (Book book : books) {
            if (book.getIdAccount().getIdAccount() == idAccount) b = book;
        }
        return b;
    }

    @Override
    public List<Book> readyAll() {
        List<Book> books = em.createNamedQuery("Book.findAll", Book.class).getResultList();
        return books;
    }
    
}
