package com.br.lp3.command;

import com.br.lp3.model.daos.BookDAO;
import com.br.lp3.model.daos.IndicationDAO;
import com.br.lp3.model.daos.MovieDAO;
import com.br.lp3.model.entities.Indication;
import com.br.lp3.model.entities.Movie;
import googlebooks.ws.BookParser;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import youtubevideos.ws.VideoParser;
import youtubevideos.ws.entities.Video;

/**
 *
 * @author ezequieloliveira
 */
public class SearchCommand implements Command {

    IndicationDAO indicationDAO = lookupIndicationDAOBean();
    MovieDAO movieDAO = lookupMovieDAOBean();
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
            
            List<Video> videos = (List<Video>) request.getSession().getAttribute("trailers");
            Movie movie = new Movie();
            Video video = videos.get(0);
            System.out.println(video.toString());
            movie.setTitle(video.getSnippet().getTitle().toUpperCase());
            System.out.println(movie.toString());
            
            com.br.lp3.model.entities.Movie savedMovie = movieDAO.readyByName(movie.getTitle());
            
            if(savedMovie == null)
            {
                movieDAO.insert(movie);
                savedMovie = movie;
            }
            
            System.out.println("FILME SALVO" + savedMovie.toString());
            System.out.println("MOVIE SALVO" + movie.toString());
            if (savedBook == null) {
                com.br.lp3.model.entities.Book book = new com.br.lp3.model.entities.Book();
                book.setIsbn(apiBook.getIsbn());
                book.setIdAccount(currentUser);
                bookDAO.insert(book);
                savedBook = book;
            }
            System.out.println("LIVRO SALVO" + savedBook.toString());
            
            Indication indication = indicationDAO.readyByISBN(savedBook.getIsbn());
            
            if(indication == null)
            {
                Indication indication1 = new Indication();
                indication1.setIdBook(savedBook);
                indication1.setIdMovie(savedMovie);
                indication1.setCommentList(new ArrayList());
                indicationDAO.insert(indication1);
                indication = indication1;
            }
            
            request.getSession().setAttribute("indicacao", indication);
            request.getSession().setAttribute("comentarios", indication.getCommentList());
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

    private MovieDAO lookupMovieDAOBean() {
        try {
            Context c = new InitialContext();
            return (MovieDAO) c.lookup("java:global/SuggMovies/SuggMovies-ejb/MovieDAO!com.br.lp3.model.daos.MovieDAO");
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
