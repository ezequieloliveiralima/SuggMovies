/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package com.br.lp3.command;

import com.br.lp3.exceptions.SigninEmailException;
import com.br.lp3.exceptions.SigninPassException;
import com.br.lp3.model.daos.AccountDAO;
import com.br.lp3.model.daos.GenericDAO;
import com.br.lp3.model.daos.ProfileAccountDAO;
import com.br.lp3.model.entities.Account;
import com.br.lp3.model.entities.ProfileAccount;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 41488350
 */
public class UserCommand implements Command {

    ProfileAccountDAO profileAccountDAO = lookupProfileAccountDAOBean();
    AccountDAO accountDAO = lookupAccountDAOBean();

    private HttpServletRequest request;
    private HttpServletResponse response;
    private String returnPage = "index.jsp";

    @Override
    public void init(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void execute() {
        String action = request.getParameter("action");
        if ("login".equals(action)) {
            signin();
        } else if ("registrar".equals(action)) {
            signup();
        } else if ("logout".equals(action)) {
            logout();
        } else if ("view-profile".equals(action)) {
            viewProfile();
        }
    }

    @Override
    public String getReturnPage() {
        return returnPage;
    }

    private void signin() {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Account user;
        returnPage = "index.jsp";
        try {
            user = accountDAO.readyByEmail(email);

            if (!user.getPassword().equals(password)) {
                throw new SigninPassException();
            } else {
                request.getSession().setAttribute("currentProfile", profileAccountDAO.readById(user.getIdAccount()));
                request.getSession().setAttribute("user", user);
                returnPage = "home.jsp";
            }
        } catch (SigninEmailException | SigninPassException ex) {
            request.getSession().setAttribute("errorMsg", ex.getMessage());
        }
    }

    private void signup() {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String bday = request.getParameter("bday");
        String name = request.getParameter("name");

        Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);

        ProfileAccount pa = new ProfileAccount();
        pa.setName(name);
        pa.setBirthDate(new Date());
        pa.setSignupDate(new Date());

        pa.setAccount(account);
        account.setProfileAccount(pa);
        accountDAO.insert(account);

        returnPage = "index.jsp";
    }

    private void logout() {
        request.getSession().invalidate();
        returnPage = "index.jsp";
    }

    private void viewProfile() {
        request.getSession().setAttribute("profile", profileAccountDAO.readById(Integer.parseInt(request.getParameter("id"))));
        returnPage = "profile.jsp";
    }

    private AccountDAO lookupAccountDAOBean() {
        try {
            Context c = new InitialContext();
            return (AccountDAO) c.lookup("java:global/SuggMovies/SuggMovies-ejb/AccountDAO!com.br.lp3.model.daos.AccountDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private ProfileAccountDAO lookupProfileAccountDAOBean() {
        try {
            Context c = new InitialContext();
            return (ProfileAccountDAO) c.lookup("java:global/SuggMovies/SuggMovies-ejb/ProfileAccountDAO!com.br.lp3.model.daos.ProfileAccountDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
