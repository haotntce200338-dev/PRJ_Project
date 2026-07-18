<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<div class="detail-nav">
    <a href="javascript:history.back()">
        <i class="bi bi-arrow-left"></i>
        Back
    </a>

    <a href="${pageContext.request.contextPath}/movies">
        <i class="bi bi-film"></i>
        All Movies
    </a>
</div>

<div class="detail-container">

    <main class="detail-main">

        <div class="movie-detail-card">

            <img class="movie-poster"
                 src="${movie.poster}"
                 alt="${movie.title}">

            <div class="detail-info">

                <h1>${movie.title}</h1>

                <div class="rating">
                    ⭐ ${movie.rating}/10
                </div>

                <div class="movie-genres">
                    <c:forTokens items="${movie.genres}"
                                 delims=","
                                 var="genre">
                        <span class="genre-tag">
                            ${genre}
                        </span>
                    </c:forTokens>
                </div>

                <p>
                    <strong>Duration:</strong>
                    ${movie.duration} minutes
                </p>

                <p>
                    <strong>Release Date:</strong>
                    ${movie.releaseDate}
                </p>

                <p>
                    <strong>Director:</strong>
                    ${movie.director}
                </p>

                <p>
                    <strong>Cast:</strong>
                    ${movie.cast}
                </p>

                <div class="detail-buttons">

                    <button class="btn btn-warning"
                            data-bs-toggle="modal"
                            data-bs-target="#bookingModal">
                        Book Ticket
                    </button>

                </div>

            </div>

        </div>

        <section class="overview">

            <h2>Overview</h2>

            <p>
                ${movie.description}
            </p>

        </section>

        <section class="reviews">

            <h2>Reviews</h2>

            <c:choose>

                <c:when test="${empty reviews}">
                    <p>No reviews yet.</p>
                </c:when>

                <c:otherwise>

                    <c:forEach items="${reviews}" var="r">

                        <div class="review-card">

                            <div class="review-rating">

                                <c:forEach begin="1"
                                           end="${r.rating}">
                                    ⭐
                                </c:forEach>

                            </div>

                            <p>${r.comment}</p>

                            <small>
                                - ${r.user.fullName}
                            </small>

                        </div>

                    </c:forEach>

                </c:otherwise>

            </c:choose>

        </section>

    </main>

</div>

<%@include file="/WEB-INF/include/booking-modal.jsp"%>
<%@include file="/WEB-INF/include/footer.jsp"%>