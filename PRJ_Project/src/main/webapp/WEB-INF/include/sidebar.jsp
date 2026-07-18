<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="sidebar">
    <div class="sidebar-section">

        <a href="${pageContext.request.contextPath}/home"
           class="sidebar-item active">
            <i class="bi bi-house-door"></i>
            Home
        </a>

        <a href="${pageContext.request.contextPath}/movies"
           class="sidebar-item">
            <i class="bi bi-film"></i>
            Movies
        </a>

        <a href="${pageContext.request.contextPath}/top"
           class="sidebar-item">
            <i class="bi bi-star"></i>
            Top Rated
        </a>

        <a href="${pageContext.request.contextPath}/my-tickets"
           class="sidebar-item">
            <i class="bi bi-ticket-perforated"></i>
            My tickets
        </a>

    </div>
    <div class="sidebar-dropdown">
        <div class="sidebar-item dropdown-btn">
            <i class="bi bi-collection"></i>
            Genres
            <i class="bi bi-chevron-down arrow"></i>
        </div>
        <div class="dropdown-content">
            <a href="${pageContext.request.contextPath}/search?keyword=Action">
                Action
            </a>
            <a href="${pageContext.request.contextPath}/search?keyword=Sci-Fi">
                Sci-Fi
            </a>
            <a href="${pageContext.request.contextPath}/search?keyword=Adventure">
                Adventure
            </a>
        </div>

    </div>

</div>

</html>
