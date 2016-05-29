/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package com.br.lp3.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 41488350
 */
public class UserCommand implements Command {
    
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
        }
    }

    @Override
    public String getReturnPage() {
        return returnPage;
    }
    
    private void login() {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        returnPage = "rodrigo@gmail.com".equals(email) && "123".equals(password) ? "index.jsp" : "login";
    }
    
}
