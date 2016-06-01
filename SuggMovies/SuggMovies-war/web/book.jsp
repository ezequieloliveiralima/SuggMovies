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
        <meta charset="UTF-8">
        <title>${viewItem.title}</title>
        <link href="css/book.css" rel="stylesheet" type="text/css"/>
        <link href="css/comment.css" rel="stylesheet" type="text/css"/>
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
        <br>
        <p><b>Trailer</b></p>
        <div class="trailer">
            <c:forEach items="${trailers}" var="trailer">
                <iframe width="420" height="315"
                        src="http://www.youtube.com/embed/${trailer.id.videoId}"></iframe>
                </c:forEach>
        </div>

        <p><b>Comentários</b></p>
        <div class="comentario">
            <form action="suggmovies" method="POST">
                <input type="hidden" name="command" value="Comment">
                <input type="hidden" name="action" value="enviar">
                <textarea name="comment" rows="4" cols="50">
                    Escreva seu comentário
                </textarea>
                <input type="submit" value="Enviar">
            </form>
        </div>
        <br>
        <c:forEach items="${comentarios}" var="comentario">
            <div>
                <div class="comment">
                    <c:out value="${comentario.idAccount.email}: ${comentario.comment}"/>
                </div>
            </div>
        </c:forEach>
    </body>
</html>
