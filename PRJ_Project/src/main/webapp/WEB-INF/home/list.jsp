<%-- 
    Document   : movie-list
    Created on : Jun 27, 2026, 4:17:15 AM
    Author     : User
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Movie"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file = "/WEB-INF/include/header.jsp" %>
<h1>Movie Management</h1>
<!DOCTYPE html>
<html>
    <head>

        <title>Movie Management</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
              rel="stylesheet">

    </head>


    <body class="bg-light">


        <div class="container mt-5">


            <!-- HEADER -->

            <div class="d-flex justify-content-between align-items-center mb-4">

                <h2 class="fw-bold">
                    🎬 Movie Management
                </h2>


                <a href="movie/create.jsp" 
                   class="btn btn-primary">

                    + Add Movie

                </a>


            </div>




            <!-- TABLE CARD -->

            <div class="card shadow">


                <div class="card-body">


                    <table class="table table-hover align-middle">


                        <thead class="table-dark">

                            <tr>

                                <th>ID</th>

                                <th>Poster</th>

                                <th>Title</th>

                                <th>Genre</th>

                                <th>Description</th>

                                <th>Status</th>

                                <th>Action</th>

                            </tr>


                        </thead>



                        <tbody>


                            <%

                                ArrayList<Movie> list
                                        = (ArrayList<Movie>) request.getAttribute("movieList");

                                if (list != null) {

                                    for (Movie m : list) {

                            %>


                            <tr>


                                <td>
                                    <%=m.getMovieID()%>
                                </td>



                                <td>

                                    <img src="<%=m.getPoster()%>"
                                         width="80"
                                         height="100"
                                         style="object-fit:cover">

                                </td>



                                <td>

                                    <strong>
                                        <%=m.getTitle()%>
                                    </strong>

                                    <br>

                                    <small>
                                        <%=m.getDuration()%> minutes
                                    </small>


                                </td>




                                <td>

                                    <%=m.getGenre().getGenreName()%>

                                </td>




                                <td width="250">

                                    <%=m.getDescription()%>

                                </td>




                                <td>


                                    <%
                                        if (m.getStatus().equals("Active")) {
                                    %>

                                    <span class="badge bg-success">
                                        Active
                                    </span>


                                    <%
                                    } else {
                                    %>

                                    <span class="badge bg-secondary">
                                        Inactive
                                    </span>


                                    <%
                                        }
                                    %>


                                </td>




                                <td>


                                    <a href="movie/edit?id=<%=m.getMovieID()%>"
                                       class="btn btn-warning btn-sm">

                                        Edit

                                    </a>



                                    <a href="movie/delete?id=<%=m.getMovieID()%>"
                                       class="btn btn-danger btn-sm"
                                       onclick="return confirm('Delete this movie?')">

                                        Delete

                                    </a>


                                </td>


                            </tr>


                            <%

                                    }

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