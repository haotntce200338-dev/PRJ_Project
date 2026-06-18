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
    </head>
</head>
<body>

    <nav class="navbar navbar-expand-lg main-navbar">

        <div class="container-fluid px-4">

            <!-- Logo -->
            <a class="navbar-brand logo" href="#">
                CinemaHub
            </a>

            <!-- Mobile Menu -->
            <button class="navbar-toggler"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#navbarContent">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse"
                 id="navbarContent">

                <!-- Left Menu -->
                <ul class="navbar-nav ms-4">
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            Home
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            Now Showing
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            Coming Soon
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            Cinemas
                        </a>
                    </li>
                </ul>

                <!-- Search -->
                <form class="d-flex mx-auto search-form">
                    <input class="form-control search-box"
                           type="search"
                           placeholder="Search movies...">
                </form>

                <!-- Right Menu -->
                <div class="d-flex align-items-center gap-2">

                    <a class="btn my-ticket-btn" href="#">
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

</body>
</html>
