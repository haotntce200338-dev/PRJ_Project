<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<%@include file="/WEB-INF/include/header.jsp"%>

<div class="search-container">

    <c:choose>

        <c:when test="${empty movies}">

            <h3>No movie found.</h3>

        </c:when>

        <c:otherwise>

            <c:forEach var="movie" items="${movies}">

                <c:set var="movie" value="${movie}" scope="request"/>

                <jsp:include page="/WEB-INF/include/movie-card.jsp"/>

            </c:forEach>

        </c:otherwise>

    </c:choose>

</div>

<%@include file="/WEB-INF/include/footer.jsp"%>