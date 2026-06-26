<%-- 
    Document   : home
    Created on : Jun 20, 2026, 9:05:49 PM
    Author     : MY_PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp"%>

<div class="page-layout">

    <%@include file="/WEB-INF/include/sidebar.jsp"%>

    <div class="main-content">

        <section class="movie-section">
            <h2 class="section-title">Now Showing</h2>

            <div class="movie-grid">
                <!-- Movie cards -->
            </div>
        </section>

        <section class="movie-section mt-5">
            <h2 class="section-title">Coming Soon</h2>

            <div class="movie-grid">
                <!-- Movie cards -->
            </div>
        </section>

        <section class="movie-section mt-5">
            <h2 class="section-title">Top Movies</h2>

            <div class="movie-grid">
                <!-- Movie cards -->
            </div>
        </section>

    </div>

</div>

<%@include file="/WEB-INF/include/footer.jsp"%>