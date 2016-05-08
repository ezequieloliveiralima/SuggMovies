<%-- 
    Document   : edit-profile
    Created on : 07/05/2016, 12:40:29
    Author     : ezequieloliveira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="./css/default.css" type="text/css" rel="stylesheet">
        <link href="./css/profile.css" type="text/css" rel="stylesheet">
        <meta charset="utf-8">
        <title>SuggMovies - Editar perfil</title>
    </head>
    <body>
        <%@include file="WEB-INF/logged-header.jspf" %>
        <form>
            <input type="hidden" name="command" value="User">
            <input type="hidden" name="action" value="edit-profile">
            <span>
                <input type="text" name="name" placeholder="Nome completo" value="${currentProfile.name}">
            </span>
            <span>
                <input type="date" name="bday" value="">
            </span>
            <span>
                <button></button>
            </span>
        </form>
    </body>
</html>
