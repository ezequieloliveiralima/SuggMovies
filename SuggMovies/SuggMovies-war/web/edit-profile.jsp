<%-- 
    Document   : edit-profile
    Created on : 07/05/2016, 12:40:29
    Author     : ezequieloliveira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="suggmovies" uri="/WEB-INF/tlds/minhabiblioteca.tld" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="./css/default.css" type="text/css" rel="stylesheet">
        <link href="css/edit-profile.css" rel="stylesheet" type="text/css"/>
        <meta charset="utf-8">
        <title>SuggMovies - Editar perfil</title>
    </head>
    <body>
        <%@include file="WEB-INF/logged-header.jspf" %>
        <c:if test="${errorMsg != null}">
            <div class="msg">
                ${errorMsg}
            </div>
            <c:set var="errorMsg" value="${null}"></c:set>
        </c:if>
        <div id="content">
            <form action="suggmovies" method="post">
                <input type="hidden" name="command" value="User">
                <input type="hidden" name="action" value="edit-profile">
                <span>Digite sua senha antiga</span>
                <span>
                    <input type="text" name="name" placeholder="Nome completo" value="${currentProfile.name}">
                </span>
                <span>
                    <suggmovies:myDate date="${currentProfile.birthDate}" name="bday"></suggmovies:myDate>
                </span>
                <span>
                    <input type="password" name="oldPass" placeholder="Senha antiga" required="">
                </span>
                <span>
                    <input type="password" name="newPass" placeholder="Nova senha">
                </span>
                <span>
                    <button>Salvar</button>
                </span>
            </form>
        </div>
    </body>
</html>
