<%-- 
    Document   : home
    Created on : 08/03/2016, 08:39:49
    Author     : 41488350
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="./css/default.css" type="text/css" rel="stylesheet">
        <link href="./css/home.css" type="text/css" rel="stylesheet">
        <meta charset="utf-8">
        <script src="javascript/default.js" type="text/javascript"></script>
        <title></title>
    </head>
    <body>
        <%@include file="WEB-INF/logged-header.jspf" %>
        <section>
            <div class="c-query">
                <form action="suggmovies" method="get">
                    <input type="hidden" name="command" value="Search">
                    <input type="hidden" name="action" value="q">
                    <input type="search" name="q" id="q" placeholder="Busque aqui titulos de livros" autocomplete="off" autofocus="true">
                </form>
            </div>
            <div id="content">
                
            </div>
        </section>
    </body>
</html>
<script src="javascript/home.js" type="text/javascript"></script>