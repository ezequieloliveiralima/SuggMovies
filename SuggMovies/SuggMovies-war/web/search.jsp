<%-- 
    Document   : search
    Created on : 07/05/2016, 08:55:46
    Author     : ezequieloliveira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="./css/default.css" type="text/css" rel="stylesheet">
        <link href="./css/search.css" type="text/css" rel="stylesheet">
    </head>
    <body>
        <%@include file="WEB-INF/logged-header.jspf" %>
        <div class="go-back">
            <a href="javascript:window.history.back();">Voltar</a>
        </div>
        <section>
            <article class="itemSearch">
                <c:forEach items="${items}" var="item" varStatus="loop">
                    <a href="suggmovies?command=Search&action=view&i=${loop.index}"><img src="${item.thumbnail}"></a>
                </c:forEach>
            </article>
        </section>
    </body>
</html>
