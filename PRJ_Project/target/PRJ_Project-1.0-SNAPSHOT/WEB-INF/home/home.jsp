<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

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

            <c:forEach var="movie" items="${nowShowing}">
                <c:set var="movie" value="${movie}" scope="request"/>
                <jsp:include page="/WEB-INF/include/movie-card.jsp"/>
            </c:forEach>

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

            <c:forEach var="movie" items="${nowShowing}">
                <c:set var="movie" value="${movie}" scope="request"/>
                <jsp:include page="/WEB-INF/include/movie-card.jsp"/>
            </c:forEach>

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

            <c:forEach var="movie" items="${nowShowing}">
                <c:set var="movie" value="${movie}" scope="request"/>
                <jsp:include page="/WEB-INF/include/movie-card.jsp"/>
            </c:forEach>

        </section>

    </main>

</div>

<script src="${pageContext.request.contextPath}/assets/js/sidebar.js"></script>
<%@include file="/WEB-INF/include/footer.jsp"%>