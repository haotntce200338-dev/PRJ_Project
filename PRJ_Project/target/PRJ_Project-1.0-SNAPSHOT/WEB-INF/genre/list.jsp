<%-- 
    Document   : list
    Created on : Jul 12, 2026, 2:40:51 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Genre"%>

<%
    List<Genre> list = (List<Genre>) request.getAttribute("genreList");
%>

<%@include file = "/WEB-INF/include/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>

        <meta charset="UTF-8">

        <title>Genre Management</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

        <style>

            body{
                background:#f4f6f9;
            }

            .card{
                margin-top:40px;
                border-radius:15px;
                box-shadow:0 5px 15px rgba(0,0,0,.15);
            }

            h2{
                font-weight:bold;
            }

            table td{
                vertical-align:middle;
            }

        </style>

    </head>

    <body>

        <div class="container">

            <div class="card">

                <div class="card-header bg-dark text-white d-flex justify-content-between align-items-center">

                    <h2>Genre Management</h2>

                    <a href="genre?action=create"
                       class="btn btn-success">

                        + Add Genre

                    </a>

                </div>

                <div class="card-body">

                    <table class="table table-bordered table-hover text-center">

                        <thead class="table-dark">

                            <tr>

                                <th width="15%">ID</th>

                                <th>Genre Name</th>

                                <th width="25%">Action</th>

                            </tr>

                        </thead>

                        <tbody>

                        <%

                            for(Genre g : list){

                        %>

                        <tr>

                            <td>

                                <%= g.getGenreID()%>

                            </td>

                            <td>

                                <%= g.getGenreName()%>

                            </td>

                            <td>

                                <a href="genre?action=edit&id=<%=g.getGenreID()%>"
                                   class="btn btn-warning btn-sm">

                                    Edit

                                </a>

                                <a href="genre?action=delete&id=<%=g.getGenreID()%>"
                                   class="btn btn-danger btn-sm"
                                   onclick="return confirm('Are you sure you want to delete this genre?');">

                                    Delete

                                </a>

                            </td>

                        </tr>

                        <%

                            }

                        %>

                        </tbody>

                    </table>

                </div>

            </div>

        </div>

    </body>

</html>
<%@include file="/WEB-INF/include/footer.jsp" %>