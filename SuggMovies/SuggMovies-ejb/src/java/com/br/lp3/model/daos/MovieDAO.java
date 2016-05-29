
package com.br.lp3.model.daos;

import com.br.lp3.model.entities.Movie;
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
public class MovieDAO implements GenericDAO<Movie>{

    @PersistenceContext(unitName = "SuggMovies-ejbPU", type = PersistenceContextType.TRANSACTION)
    
    EntityManager em;
    
    @Override
    public void insert(Movie e) {
        em.persist(e);
    }

    @Override
    public void update(Movie e) {
        em.merge(e);
    }

    @Override
    public void remove(Movie e) {
        em.remove(e);
    }

    @Override
    public Movie readById(long id) {
        Movie movie = em.find(Movie.class, id);
        return movie;
    }

    @Override
    public List<Movie> readyAll() {
        List<Movie> movies = em.createNamedQuery("Movie.findAll", Movie.class).getResultList();
        return movies;
    }
    
}
