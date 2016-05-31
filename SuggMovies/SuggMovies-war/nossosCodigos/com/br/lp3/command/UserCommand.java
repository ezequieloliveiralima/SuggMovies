/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package com.br.lp3.command;

import com.br.lp3.exceptions.SigninEmailException;
import com.br.lp3.exceptions.SigninPassException;
import com.br.lp3.logger.LoggerBeanInterface;
import com.br.lp3.logger.consumer.LoggerConsumer;
import com.br.lp3.model.daos.AccountDAO;
import com.br.lp3.model.daos.GenericDAO;
import com.br.lp3.model.daos.ProfileAccountDAO;
import com.br.lp3.model.entities.Account;
import com.br.lp3.model.entities.ProfileAccount;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
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
        } else if ("edit-profile".equals(action)) {
            editProfile();
        }
    }

    @Override
    public String getReturnPage() {
        return returnPage;
    }

    private void signin() {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        String log = email + " - login";

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
                sendJMSMessageToMyQueue(log);
            }
        } catch (SigninEmailException | SigninPassException ex) {
            request.getSession().setAttribute("errorMsg", ex.getMessage());
        } catch (JMSException ex) {
            Logger.getLogger(UserCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(UserCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void signup() {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String bday = request.getParameter("bday");
        String name = request.getParameter("name");

        try {
            accountDAO.readyByEmail(email);
            request.getSession().setAttribute("errorMsg", "E-mail já cadastrado.");
        } catch (Exception e) {
            Account account = new Account();
            account.setEmail(email);
            account.setPassword(password);

            ProfileAccount pa = new ProfileAccount();
            pa.setName(name);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();

            try {
                date = formatter.parse(bday);
            } catch (ParseException ex1) {
                System.out.println(ex1);
            }
            pa.setBirthDate(date);
            pa.setSignupDate(new Date());

            pa.setAccount(account);
            account.setProfileAccount(pa);
            accountDAO.insert(account);
        }

        returnPage = "index.jsp";
    }

    private void logout() {
        request.getSession().invalidate();
        returnPage = "index.jsp";
    }

    private void viewProfile() {
        ProfileAccount pa = profileAccountDAO.readById(Integer.parseInt(request.getParameter("id")));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        request.getSession().setAttribute("profileBirth", formatter.format(pa.getBirthDate()));
        request.getSession().setAttribute("profile", pa);
        returnPage = "profile.jsp";
    }

    private void editProfile() {
        ProfileAccount pa = (ProfileAccount) request.getSession().getAttribute("currentProfile");
        Account a = (Account) request.getSession().getAttribute("user");

        String oldPass = request.getParameter("oldPass");
        String newPass = request.getParameter("newPass");
        String name = request.getParameter("name");
        String bDay = request.getParameter("bday");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        try {
            date = formatter.parse(bDay);
        } catch (ParseException ex1) {
            System.out.println(ex1);
        }

        pa.setBirthDate(date);
        pa.setName(name);
        if (a.getPassword().equals(oldPass)) {
            if (!newPass.equals("")) {
                a.setPassword(newPass);
            }
            accountDAO.update(a);
            profileAccountDAO.update(pa);
        } else {
            request.getSession().setAttribute("errorMsg", "Senha antiga errada.");
        }

        returnPage = "edit-profile.jsp";
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

    private Message createJMSMessageForjmsLogger(Session session, Object messageData) throws JMSException {
        // TODO create and populate message to send
        TextMessage tm = session.createTextMessage();
        tm.setText(messageData.toString());
        return tm;
    }

    private Message createJMSMessageForjmsMyQueue(Session session, Object messageData) throws JMSException {
        // TODO create and populate message to send
        TextMessage tm = session.createTextMessage();
        tm.setText(messageData.toString());
        return tm;
    }

    private void sendJMSMessageToMyQueue(Object messageData) throws JMSException, NamingException {
        Context c = new InitialContext();
        ConnectionFactory cf = (ConnectionFactory) c.lookup("java:comp/env/java:comp/DefaultJMSConnectionFactory");
        Connection conn = null;
        Session s = null;
        try {
            conn = cf.createConnection();
            s = conn.createSession(false, s.AUTO_ACKNOWLEDGE);
            Destination destination = (Destination) c.lookup("java:comp/env/jms/myQueue");
            MessageProducer mp = s.createProducer(destination);
            mp.send(createJMSMessageForjmsMyQueue(s, messageData));
        } finally {
            if (s != null) {
                try {
                    s.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
                }
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

}
