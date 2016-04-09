<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="./css/default.css" type="text/css" rel="stylesheet">
        <link href="./css/profile.css" type="text/css" rel="stylesheet">
        <meta charset="utf-8">
        <title>SuggMovies - Perfil de: ${nome}</title>
    </head>
    <body>
        <%@include file="WEB-INF/logged-header.jspf" %>
        <div id="header-profile">
            <ul>
                <li><img src="images/no-avatar.png" id="profile-image"></li>
                <li class="fullname">${nome}</li>
            </ul>
        </div>
    </body>
</html>
