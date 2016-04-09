<%-- 
    Document   : index
    Created on : 09/03/2016, 09:01:24
    Author     : 41488350
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="./css/default.css" type="text/css" rel="stylesheet">
        <link href="./css/index.css" type="text/css" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="WEB-INF/unlogged-header.jspf" %>
        <section>
            <form action="suggMovies" method="post">
                <input type="hidden" name="command" value="Login">
                <span><input type="email" name="email" placeholder="E-mail" required="true"></span>
                <span><input type="password" name="password" placeholder="Senha" required="true"></span>
                <span><input type="checkbox" name="keepConnected" id="keepConnected"><label for="keepConnected">manter conectado</label></span>
                <span><button>Entrar</button></span>
            </form>
        </section>
    </body>
</html>
