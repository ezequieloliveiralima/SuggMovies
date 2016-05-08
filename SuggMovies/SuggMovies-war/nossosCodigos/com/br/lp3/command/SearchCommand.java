package com.br.lp3.command;

import googlebooks.ws.BookParser;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ezequieloliveira
 */
public class SearchCommand implements Command {
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
        } else if ("view".equals(request.getParameter("action"))) {
            List<googlebooks.ws.entities.Book> books = (List<googlebooks.ws.entities.Book>) request.getSession().getAttribute("items");
            request.getSession().setAttribute("viewItem", books.get(Integer.parseInt(request.getParameter("i"))));
            returnPage = "book.jsp";
        }
    }

    @Override
    public String getReturnPage() {
        return returnPage;
    }
    
}
