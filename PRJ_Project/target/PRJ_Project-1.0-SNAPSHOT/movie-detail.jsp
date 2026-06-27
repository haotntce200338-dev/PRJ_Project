<%-- 
    Document   : movie-detail
    Created on : Jun 27, 2026, 11:45:20 PM
    Author     : MY_PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp"%>

<div class="page-layout">

    <%@include file="/WEB-INF/include/sidebar.jsp"%>

    <main class="main-content">

        <div class="movie-detail-card">

            <img src="${pageContext.request.contextPath}/assets/images/avenger.jsp"
                 class="detail-poster">

            <div class="detail-info">

                <h1>Avengers: Endgame</h1>

                <div class="rating">
                    ⭐ 8.9/10
                </div>

                <div class="movie-genres">

                    <span class="genre-tag">Action</span>

                    <span class="genre-tag">Adventure</span>

                    <span class="genre-tag">Sci-Fi</span>

                </div>

                <p><strong>Duration:</strong> 181 minutes</p>

                <p><strong>Release Date:</strong> April 26, 2019</p>

                <p><strong>Director:</strong> Anthony Russo</p>

                <p><strong>Cast:</strong> Robert Downey Jr., Chris Evans...</p>

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

                After the devastating events of Avengers:
                Infinity War, the universe is in ruins.
                The remaining Avengers must reunite
                to undo the damage caused by Thanos.

            </p>

        </section>

        <section class="reviews">

            <h2>Reviews</h2>

            <div class="review-card">

                ⭐⭐⭐⭐⭐

                <p>

                    One of the greatest superhero movies ever made.

                </p>
            </div>

            <div class="review-card">

                ⭐⭐⭐⭐☆

                <p>

                    Emotional ending with amazing action scenes.

                </p>

            </div>

        </section>

    </main>

</div>

<%@include file="/WEB-INF/include/booking-modal.jsp"%>
<%@include file="/WEB-INF/include/footer.jsp"%>