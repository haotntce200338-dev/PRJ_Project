<%-- 
    Document   : edit
    Created on : Jul 12, 2026, 2:41:15 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Genre"%>

<%
    Genre genre = (Genre) request.getAttribute("genre");
%>

<%@include file = "/WEB-INF/include/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Edit Genre</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

        <style>

            body{
                background:#f5f5f5;
            }

            .card{
                margin-top:60px;
                border-radius:15px;
                box-shadow:0px 0px 15px rgba(0,0,0,.2);
            }

        </style>

    </head>

    <body>

        <div class="container">

            <div class="row justify-content-center">

                <div class="col-md-6">

                    <div class="card">

                        <div class="card-header bg-warning text-dark">

                            <h3>Edit Genre</h3>

                        </div>

                        <div class="card-body">

                            <form action="genre?action=edit" method="post">

                                <input type="hidden"
                                       name="genreID"
                                       value="<%= genre.getGenreID()%>">

                                <div class="mb-3">

                                    <label class="form-label">
                                        Genre Name
                                    </label>

                                    <input
                                        type="text"
                                        class="form-control"
                                        name="genreName"
                                        value="<%= genre.getGenreName()%>"
                                        required>

                                </div>

                                <button class="btn btn-warning">

                                    Update

                                </button>

                                <a href="genre"
                                   class="btn btn-secondary">

                                    Cancel

                                </a>

                            </form>

                        </div>

                    </div>

                </div>

            </div>

        </div>

    </body>
</html>
<%@include file="/WEB-INF/include/footer.jsp" %>