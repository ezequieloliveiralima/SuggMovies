package com.br.lp3.command;

import com.br.lp3.model.daos.BookDAO;
import googlebooks.ws.BookParser;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import youtubevideos.ws.VideoParser;

/**
 *
 * @author ezequieloliveira
 */
public class SearchCommand implements Command {
    BookDAO bookDAO = lookupBookDAOBean();
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String returnPage = "search.jsp";

    @Override
    public void init(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void execute() {
        if ("q".equals(request.getParameter("action"))) {
            request.getSession().setAttribute("items", BookParser.read(request.getParameter("q").replace(" ", "+")));
            request.getSession().setAttribute("trailers", VideoParser.parseFeed(request.getParameter("q").replace(" ", "+")));
        } else if ("view".equals(request.getParameter("action"))) {
            List<googlebooks.ws.entities.Book> books = (List<googlebooks.ws.entities.Book>) request.getSession().getAttribute("items");
            googlebooks.ws.entities.Book apiBook = books.get(Integer.parseInt(request.getParameter("i")));
            request.getSession().setAttribute("viewItem", apiBook);
            
            com.br.lp3.model.entities.Account currentUser = (com.br.lp3.model.entities.Account) request.getSession().getAttribute("user");
            com.br.lp3.model.entities.Book savedBook = bookDAO.readByIsbn(apiBook.getIsbn(), currentUser.getIdAccount());
            
            if (savedBook == null) {
                com.br.lp3.model.entities.Book book = new com.br.lp3.model.entities.Book();
                book.setIsbn(apiBook.getIsbn());
                book.setIdAccount(currentUser);
                bookDAO.insert(book);
            }
            
            returnPage = "book.jsp";
        }
    }

    @Override
    public String getReturnPage() {
        return returnPage;
    }

    private BookDAO lookupBookDAOBean() {
        try {
            Context c = new InitialContext();
            return (BookDAO) c.lookup("java:global/SuggMovies/SuggMovies-ejb/BookDAO!com.br.lp3.model.daos.BookDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
