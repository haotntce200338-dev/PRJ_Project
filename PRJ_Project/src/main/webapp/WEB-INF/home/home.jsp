<%-- 
    Document   : home
    Created on : Jun 20, 2026, 9:05:49 PM
    Author     : MY_PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp"%>

<div class="page-layout">

    <%@include file="/WEB-INF/include/sidebar.jsp"%>

    <main class="main-content">

            <!-- NOW SHOWING -->
            <section class="movie-section">

                <div class="section-header">

                    <h2>
                        Now Showing
                    </h2>

                    <a href="#">
                        View All >
                    </a>

                </div>

                <%@include file="/WEB-INF/include/movie-card.jsp"%>
                <%@include file="/WEB-INF/include/movie-card.jsp"%>

            </section>

            <!-- COMING SOON -->

            <section class="movie-section">
                <div class="section-header">

                    <h2>
                        Coming Soon
                    </h2>

                    <a href="#">
                        View All >
                    </a>

                </div>

                <%@include file="/WEB-INF/include/movie-card.jsp"%>
                <%@include file="/WEB-INF/include/movie-card.jsp"%>

            </section>


            <!-- TOP RATED -->
            <section class="movie-section">

                <div class="section-header">

                    <h2>
                        Top Rated
                    </h2>

                    <a href="#">
                        View All >
                    </a>

                </div>

                <%@include file="/WEB-INF/include/movie-card.jsp"%>
                <%@include file="/WEB-INF/include/movie-card.jsp"%>

            </section>

    </main>

</div>

<%@include file="/WEB-INF/include/footer.jsp"%>