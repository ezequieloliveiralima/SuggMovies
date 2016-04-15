package com.br.lp3.command;

import com.br.lp3.model.daos.GenericDAO;
import com.br.lp3.model.entities.Movie;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rodrigo T. L. Takeuti
 */
public class TesteCommand implements Command{

    GenericDAO movie = lookupmovieLocal();

    HttpServletRequest request;
    HttpServletResponse response;
    
    @Override
    public void init(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void execute() {
        
        List<Movie> movies = movie.readyAll();
        for (Movie movy : movies) {
            System.out.println(movy.toString());
        }
    }

    @Override
    public String getReturnPage() {
        return "index.jsp";
    }

    private GenericDAO lookupmovieLocal() {
        try {
            Context c = new InitialContext();
            return (GenericDAO) c.lookup("java:global/SuggMovies/SuggMovies-ejb/movie!com.br.lp3.model.daos.GenericDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
