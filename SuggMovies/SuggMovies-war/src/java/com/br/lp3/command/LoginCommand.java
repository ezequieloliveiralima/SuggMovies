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
public class LoginCommand implements Command {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private Boolean resultado;
    
    @Override
    public void init(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void execute() {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        resultado = "rodrigo@gmail.com".equals(email) && "123".equals(password);
    }

    @Override
    public String getReturnPage() {
        return resultado ? "home.jsp" : "index.jsp";
    }
    
}
