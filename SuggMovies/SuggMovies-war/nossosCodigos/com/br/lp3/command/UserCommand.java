/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package com.br.lp3.command;

import com.br.lp3.model.daos.AccountDAO;
import com.br.lp3.model.daos.GenericDAO;
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
    GenericDAO profileAccount = lookupprofileAccountLocal();
    AccountDAO accountDAO = lookupAccountDAOBean();
    
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String returnPage;

    @Override
    public void init(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void execute() {
        String action = request.getParameter("action");
        if ("login".equals(action)) {
            login();
        }else if("registrar".equals(action))
        {
            registrar();
        }
    }

    @Override
    public String getReturnPage() {
        return returnPage;
    }

    private void login() {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Account user = accountDAO.readyByEmail(email);

        returnPage = "index.jsp";
        if (user != null) {
            if (!user.getPassword().equals(password)) {
                request.getSession().setAttribute("errorMsg", "Senha inválidos");
            } else {
                returnPage = "home.jsp";
            }
        } else {
            request.getSession().setAttribute("errorMsg", "Usuário ou senha inválidos");

        }

    }
    
    public void registrar()
    {
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

    private AccountDAO lookupAccountDAOBean() {
        try {
            Context c = new InitialContext();
            return (AccountDAO) c.lookup("java:global/SuggMovies/SuggMovies-ejb/AccountDAO!com.br.lp3.model.daos.AccountDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private GenericDAO lookupprofileAccountLocal() {
        try {
            Context c = new InitialContext();
            return (GenericDAO) c.lookup("java:global/SuggMovies/SuggMovies-ejb/profileAccount!com.br.lp3.model.daos.GenericDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
