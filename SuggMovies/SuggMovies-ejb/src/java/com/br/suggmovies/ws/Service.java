package com.br.suggmovies.ws;

import com.br.lp3.model.daos.BookDAO;
import com.br.lp3.model.daos.IndicationDAO;
import com.br.lp3.model.entities.Book;
import com.br.lp3.model.entities.Indication;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author ezequieloliveira
 */
@Stateless
@Path("v1.0")
public class Service {
    @EJB
    private BookDAO bookDAO;
    @EJB
    private IndicationDAO indicationDAO;
    
    @GET
    @Produces({"application/json"})
    @Path("indications")
    public List<Indication> getIndications() {
        return indicationDAO.readyAll();
    }
    
}
