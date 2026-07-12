<%-- 
    Document   : create
    Created on : Jul 2, 2026, 1:35:44 PM
    Author     : User
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Genre"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file = "/WEB-INF/include/header.jsp" %>
<%
    List<Genre> genreList = (List<Genre>) request.getAttribute("genreList");
%>

    <head>

        <meta charset="UTF-8">

        <title>Create Movie</title>

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

                Add New Movie

            </div>

            <div class="card-body">

                <form action="home" method="post">

                    <input
                        type="hidden"
                        name="action"
                        value="create">

                    <div class="row">

                        <!-- LEFT -->

                        <div class="col-md-8">

                            <div class="mb-3">

                                <label>

                                    Movie Title

                                </label>

                                <input
                                    class="form-control"
                                    type="text"
                                    name="title"
                                    required>

                            </div>

                            <div class="mb-3">

                                <label>

                                    Description

                                </label>

                                <textarea
                                    class="form-control"
                                    rows="5"
                                    name="description"></textarea>

                            </div>

                            <div class="row">

                                <div class="col-md-6">

                                    <label>

                                        Duration

                                    </label>

                                    <input
                                        class="form-control"
                                        type="number"
                                        name="duration">

                                </div>

                                <div class="col-md-6">

                                    <label>

                                        Release Date

                                    </label>

                                    <input
                                        class="form-control"
                                        type="date"
                                        name="releaseDate">

                                </div>

                            </div>

                            <br>

                            <div class="mb-3">

                                <label>

                                    Poster URL

                                </label>

                                <input
                                    id="poster"
                                    class="form-control"
                                    type="text"
                                    name="poster"
                                    onkeyup="previewPoster()">

                            </div>

                            <div class="mb-3">

                                <label>

                                    Trailer URL

                                </label>

                                <input
                                    class="form-control"
                                    type="text"
                                    name="trailer">

                            </div>

                            <div class="row">

                                <div class="col-md-6">

                                    <label>

                                        Status

                                    </label>

                                    <select
                                        class="form-select"
                                        name="status">

                                        <option value="Showing">

                                            Showing

                                        </option>

                                        <option value="Coming Soon">

                                            Coming Soon

                                        </option>

                                        <option value="Inactive">

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

                                            for(Genre g : genreList){

                                        %>

                                        <option value="<%=g.getGenreID()%>">

                                            <%=g.getGenreName()%>

                                        </option>

                                        <%

                                            }

                                        %>

                                    </select>

                                </div>

                            </div>

                        </div>

                        <!-- RIGHT -->

                        <div class="col-md-4 text-center">

                            <img

                                id="preview"

                                class="poster"

                                src="https://placehold.co/220x320?text=Poster"

                            >

                        </div>

                    </div>

                    <hr>

                    <div class="text-center">

                        <button
                            class="btn btn-success">

                            ➕ Add Movie

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

                document.getElementById("preview").src =
                        document.getElementById("poster").value;

            }

        </script>

    </body>
<%@include file="/WEB-INF/include/footer.jsp" %>