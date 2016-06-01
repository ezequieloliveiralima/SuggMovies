package com.br.lp3.command;

import com.br.lp3.model.daos.CommentDAO;
import com.br.lp3.model.daos.IndicationDAO;
import com.br.lp3.model.entities.Account;
import com.br.lp3.model.entities.Comment;
import com.br.lp3.model.entities.Indication;
import java.util.ArrayList;
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
public class CommentCommand implements Command {

    IndicationDAO indicationDAO = lookupIndicationDAOBean();
    CommentDAO commentDAO = lookupCommentDAOBean();
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String returnPage = "book.jsp";

    @Override
    public void init(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void execute() {
        String action = request.getParameter("action");

        if ("enviar".equals(action)) {
            Indication indication = (Indication) request.getSession().getAttribute("indicacao");
            String comment = request.getParameter("comment");
            Account user = (Account) request.getSession().getAttribute("user");

            Comment comentario = new Comment();
            comentario.setComment(comment);
            comentario.setIdAccount(user);
            comentario.setIdIndication(indication);
            commentDAO.insert(comentario);

            List<Comment> comments = commentDAO.readyAll();
            List<Comment> comentarios = new ArrayList();
            for (Comment comment1 : comments) {
                if(comment1.getIdIndication().getIdIndication() == indication.getIdIndication()) comentarios.add(comment1);
            }
            
            request.getSession().setAttribute("comentarios", comentarios);
            returnPage = "book.jsp";
        }
    }

    @Override
    public String getReturnPage() {
        return returnPage;
    }

    private CommentDAO lookupCommentDAOBean() {
        try {
            Context c = new InitialContext();
            return (CommentDAO) c.lookup("java:global/SuggMovies/SuggMovies-ejb/CommentDAO!com.br.lp3.model.daos.CommentDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private IndicationDAO lookupIndicationDAOBean() {
        try {
            Context c = new InitialContext();
            return (IndicationDAO) c.lookup("java:global/SuggMovies/SuggMovies-ejb/IndicationDAO!com.br.lp3.model.daos.IndicationDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
