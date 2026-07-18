<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<a href="${pageContext.request.contextPath}/movie-detail?id=${movie.movieID}"
   class="movie-card">

    <img class="movie-poster"
         src="${pageContext.request.contextPath}/${movie.poster}"
         alt="${movie.title}">

    <div class="movie-info">

        <h3 class="movie-title">
            ${movie.title}
        </h3>

        <p class="movie-description">
            ${movie.description}
        </p>

        <div class="movie-genres">

            <c:forTokens items="${movie.genres}"
                         delims=","
                         var="genre">

                <span class="genre-tag">
                    ${genre}
                </span>

            </c:forTokens>

        </div>

    </div>

</a>