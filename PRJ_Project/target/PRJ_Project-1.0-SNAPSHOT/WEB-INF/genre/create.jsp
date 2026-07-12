<%-- 
    Document   : create
    Created on : Jul 12, 2026, 2:41:04 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file = "/WEB-INF/include/header.jsp" %>

<!DOCTYPE html>

<html>

    <head>

        <meta charset="UTF-8">

        <title>Create Genre</title>

    <form action="http://localhost:8080/PRJ_Project/genre" method="Post">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

        </head>

        <body style="background:#f4f6f9;">

            <div class="container mt-5">

                <div class="card shadow">

                    <div class="card-header bg-dark text-white">

                        <h3>Add New Genre</h3>

                    </div>

                    <div class="card-body">

                        <input
                            type="hidden"
                            name="action"
                            value="create">

                        <div class="mb-3">

                            <label class="form-label">

                                Genre Name

                            </label>

                            <input
                                type="text"
                                class="form-control"
                                name="genreName"
                                required>

                        </div>

                        <button class="btn btn-success">

                            Add Genre

                        </button>

                        <a href="genre" class="btn btn-secondary">

                            Cancel

                        </a>



                    </div>

                </div>

            </div>

        </body>
    </form>

</html>
<%@include file="/WEB-INF/include/footer.jsp" %>