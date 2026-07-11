<%-- 
    Document   : create
    Created on : Jul 2, 2026, 1:35:44 PM
    Author     : User
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.Genre"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Movie</title>
    </head>
    <body>
        <h2>Create Movie</h2>

        <form action="http://localhost:8080/PRJ_Project/home" method="post">
            <input type="hidden" name="action" value="create">

            Title
            <br>
            <input type="text" name="title">
            <br><br>

            Description
            <br>
            <textarea name="description"></textarea>
            <br><br>

            Duration
            <br>
            <input type="number" name="duration">
            <br><br>

            Release Date
            <br>
            <input type="date" name="releaseDate">
            <br><br>

            Poster
            <br>
            <input type="text" name="poster">
            <br><br>

            Trailer
            <br>
            <input type="text" name="trailer">
            <br><br>

            Status
            <br>
            <input type="text" name="status">
            <br><br>

            Genre
            <br>

            <select name="genreID">

                <c:forEach items="${genreList}" var="g">

                    <option value="${g.genreID}">
                        ${g.genreName}
                    </option>

                </c:forEach>

            </select>

            <br><br>

            <input type="submit" value="Save">


        </form>
    </body>
</html>
