<%-- 
    Document   : header
    Created on : Jun 18, 2026, 9:24:15 PM
    Author     : MY_PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CinemaHub</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
        
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sidebar.css">
        
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/home.css">
    </head>

    <body>

        <nav class="navbar navbar-expand-lg main-navbar">

            <div class="container-fluid px-4">

                <!-- Logo -->
                <a class="navbar-brand logo" href="${pageContext.request.contextPath}/home.jsp">
                    CinemaHub
                </a>

                <!-- Mobile Menu -->
                <button class="navbar-toggler"
                        type="button"
                        data-bs-toggle="collapse"
                        data-bs-target="#navbarContent">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse justify-content-end"
                     id="navbarContent">

                    <!-- Search -->
                    <form class="mx-auto search-form"
                          action="${pageContext.request.contextPath}/movies"
                          method="get">
                        <input class="form-control search-box"
                               type="search"
                               name="keyword"
                               placeholder="Search movies...">
                    </form>

                    <!-- Right Menu -->
                    <div class="d-flex align-items-center gap-2">

                        <a class="btn my-ticket-btn" href="${pageContext.request.contextPath}/my-tickets">
                            My tickets
                        </a>

                        <a class="btn btn-outline-light" href="#">
                            Sign In
                        </a>

                        <a class="btn register-btn" href="#">
                            Sign Up
                        </a>
                    </div>

                </div>


            </div>

        </nav>

        <script src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.js"></script>

