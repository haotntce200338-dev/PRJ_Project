<%-- 
    Document   : edit
    Created on : Jul 9, 2026, 7:36:45 PM
    Author     : User
--%>


<%@page import="java.util.List"%>
<%@page import="model.Genre"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Movie"%>
<%@page import="model.Genre"%>

<%@include file = "/WEB-INF/include/header.jsp" %>

<%
    Movie movie = (Movie) request.getAttribute("movie");
    List<Genre> genreList = (List<Genre>) request.getAttribute("genreList");
%>

    <head>

        <meta charset="UTF-8">

        <title>Update Movie</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

        <style>

            body{
                background:#f4f6f9;
            }

            .navbar{
                background:#212529;
            }

            .navbar-brand{
                color:white !important;
                font-size:28px;
                font-weight:bold;
            }

            .card{

                width:900px;

                margin:auto;

                margin-top:40px;

                border:none;

                border-radius:15px;

                box-shadow:0 5px 15px rgba(0,0,0,.15);

            }

            .card-header{

                background:#212529;

                color:white;

                font-size:28px;

                font-weight:bold;

                text-align:center;

            }

            label{

                font-weight:bold;

            }

            .poster{

                width:220px;

                height:320px;

                object-fit:cover;

                border-radius:10px;

                border:1px solid #ddd;

            }

        </style>

    </head>

    <body>

        <div class="card">

            <div class="card-header">

                Edit Movie

            </div>

            <div class="card-body">

                <form action="home?action=edit" method="post">

                    <input
                        type="hidden"
                        name="movieID"
                        value="<%=movie.getMovieID()%>">

                    <div class="row">

                        <!-- Left -->

                        <div class="col-md-8">

                            <div class="mb-3">

                                <label>

                                    Movie Title

                                </label>

                                <input
                                    class="form-control"
                                    type="text"
                                    name="title"
                                    value="<%=movie.getTitle()%>"
                                    required>

                            </div>

                            <div class="mb-3">

                                <label>

                                    Description

                                </label>

                                <textarea
                                    class="form-control"
                                    rows="5"
                                    name="description"><%=movie.getDescription()%></textarea>

                            </div>

                            <div class="row">

                                <div class="col-md-6">

                                    <label>

                                        Duration

                                    </label>

                                    <input
                                        class="form-control"
                                        type="number"
                                        name="duration"
                                        value="<%=movie.getDuration()%>">

                                </div>

                                <div class="col-md-6">

                                    <label>

                                        Release Date

                                    </label>

                                    <input
                                        class="form-control"
                                        type="date"
                                        name="releaseDate"
                                        value="<%=movie.getReleaseDate()%>">

                                </div>

                            </div>

                            <br>

                            <div class="mb-3">

                                <label>

                                    Poster URL

                                </label>

                                <input
                                    class="form-control"
                                    type="text"
                                    name="poster"
                                    id="poster"
                                    value="<%=movie.getPoster()%>"
                                    onkeyup="previewPoster()">

                            </div>

                            <div class="mb-3">

                                <label>

                                    Trailer URL

                                </label>

                                <input
                                    class="form-control"
                                    type="text"
                                    name="trailer"
                                    value="<%=movie.getTrailer()%>">

                            </div>

                            <div class="row">

                                <div class="col-md-6">

                                    <label>

                                        Status

                                    </label>

                                    <select
                                        class="form-select"
                                        name="status">

                                        <option
                                            value="Showing"

                                            <%=movie.getStatus().equals("Showing")?
                                                    "selected":""%>>

                                            Showing

                                        </option>

                                        <option
                                            value="Coming Soon"

                                            <%=movie.getStatus().equals("Coming Soon")?
                                                    "selected":""%>>

                                            Coming Soon

                                        </option>

                                        <option
                                            value="Inactive"

                                            <%=movie.getStatus().equals("Inactive")?
                                                    "selected":""%>>

                                            Inactive

                                        </option>

                                    </select>

                                </div>

                                <div class="col-md-6">

                                    <label>

                                        Genre

                                    </label>

                                    <select
                                        class="form-select"
                                        name="genreID">

                                        <%

                                            for(Genre g:genreList){

                                        %>

                                        <option
                                            value="<%=g.getGenreID()%>"

                                            <%=g.getGenreID()==movie.getGenre().getGenreID()
                                                    ?"selected":""%>>

                                            <%=g.getGenreName()%>

                                        </option>

                                        <%

                                            }

                                        %>

                                    </select>

                                </div>

                            </div>

                        </div>

                        <!-- Right -->

                        <div class="col-md-4 text-center">

                            <img
                                id="preview"
                                class="poster"
                                src="<%=movie.getPoster()%>">

                        </div>

                    </div>

                    <hr>

                    <div class="text-center">

                        <button
                            class="btn btn-primary">

                            💾 Update Movie

                        </button>

                        <a
                            href="home"
                            class="btn btn-secondary">

                            Cancel

                        </a>

                    </div>

                </form>

            </div>

        </div>

        <script>

            function previewPoster(){

                document.getElementById("preview").src=
                        document.getElementById("poster").value;

            }

        </script>

    </body>
<%@include file="/WEB-INF/include/footer.jsp" %>