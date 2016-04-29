package com.br.suggmovies.frontcontroller;

import com.br.lp3.command.Command;
import com.br.lp3.model.daos.GenericDAO;
import com.br.lp3.model.entities.Movie;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 41488350
 */
@WebServlet(name = "FrontController", urlPatterns = {"/suggmovies"})
public class FrontController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        String cmd = request.getParameter("command") + "Command";
        System.out.println(cmd);
        try {
            Command command = (Command) Class.forName("com.br.lp3.command." + cmd).newInstance();
            command.init(request, response);
            command.execute();
            response.sendRedirect(command.getReturnPage());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Front controller";
    }

}
