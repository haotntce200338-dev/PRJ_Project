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

        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/booking.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/movie-detail.css">
        
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/search.css">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    </head>

    <body>

        <nav class="navbar navbar-expand-lg main-navbar">

            <div class="container-fluid px-4">

                <!-- Logo -->
                <a class="navbar-brand logo" href="${pageContext.request.contextPath}/home">
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
                    <form class="mx-auto search-form" action="${pageContext.request.contextPath}/search" method="get">
                        <div class="search-container">

                            <input
                                type="text"
                                id="searchInput"
                                name="keyword"
                                placeholder="Search movies..."
                                autocomplete="off">

                            <button type="submit">
                                🔍
                            </button>

                            <div id="suggestionBox"></div>

                        </div>
                    </form>

                    <!-- Right Menu -->
                    <div class="d-flex align-items-center gap-2">

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
        <script>

            const input = document.getElementById("searchInput");

            const box = document.getElementById("suggestionBox");

            input.addEventListener("keyup", function () {

                let keyword = input.value;

                if (keyword.length === 0) {

                    box.innerHTML = "";

                    return;

                }

                fetch("${pageContext.request.contextPath}/suggest?keyword=" + encodeURIComponent(keyword))

                        .then(res => res.text())

                        .then(html => {

                            box.innerHTML = html;

                        });

            });

            document.addEventListener("click", function (e) {

                if (!box.contains(e.target) && e.target !== input) {

                    box.innerHTML = "";

                }

            });

        </script>              

        <script src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.js"></script>

