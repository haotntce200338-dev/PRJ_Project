<%-- 
    Document   : sidebar
    Created on : Jun 21, 2026, 8:44:11 PM
    Author     : MY_PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <div class="sidebar">
        <div class="sidebar-section">

            <a href="${pageContext.request.contextPath}/home.jsp"
               class="sidebar-item active">
                Home
            </a>

            <a href="${pageContext.request.contextPath}/movies"
               class="sidebar-item active">
                Movies
            </a>

            <a href="${pageContext.request.contextPath}/top"
               class="sidebar-item active">
                Top Rated
            </a>

            <a href="${pageContext.request.contextPath}/my-tickets"
               class="sidebar-item active">
                My tickets
            </a>

        </div>

        <div class="sidebar-title">
            Genres
        </div>

        <div class="sidebar-section">
            <a href="#" class="sidebar-item">
                Anime
            </a>

            <a href="#" class="sidebar-item">
                Action
            </a>

            <a href="#" class="sidebar-item">
                Drama
            </a>

            <a href="#" class="sidebar-item">
                Horror
            </a>
        </div>
    </div>

</html>
