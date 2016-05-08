
package com.br.lp3.model.daos;

import com.br.lp3.model.entities.Comment;
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
public class CommentDAO implements GenericDAO<Comment>{

    @PersistenceContext(unitName = "SuggMovies-ejbPU", type = PersistenceContextType.TRANSACTION)
    
    EntityManager em;
    
    @Override
    public void insert(Comment e) {
        em.persist(e);
    }

    @Override
    public void update(Comment e) {
        em.merge(e);
    }

    @Override
    public void remove(Comment e) {
        em.remove(e);
    }

    @Override
    public Comment readById(long id) {
        Comment movie = em.find(Comment.class, id);
        return movie;
    }

    @Override
    public List<Comment> readyAll() {
        List<Comment> movies = em.createNamedQuery("Comment.findAll", Comment.class).getResultList();
        return movies;
    }
    
}
