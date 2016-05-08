<%-- 
    Document   : book
    Created on : 07/05/2016, 09:24:19
    Author     : ezequieloliveira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/book.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="go-back">
            <a href="javascript:window.history.back();">Voltar</a>
        </div>
        <div class="title">${viewItem.title}</div>
        <div>
            <div class="description">${viewItem.description}</div>
            <div class="image"><img src="${viewItem.thumbnail}"></div>
        </div>
        <div style="font-weight: bold;">
            Categorias [
            <c:forEach items="${viewItem.categories}" var="category">
                ${category},
            </c:forEach>
            ]
        </div>
    </body>
</html>
