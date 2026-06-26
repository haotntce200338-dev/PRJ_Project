<%-- 
    Document   : movie-card
    Created on : Jun 26, 2026, 10:16:31 PM
    Author     : MY_PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="movie-card">
    
    <img class="movie-poster"
         src="${pageContext.request.contextPath}/assets/images/poster-placeholder.jsp"
         alt="Movie Poster">
    
    <div class="movie-info">
        
        <h3 class="movie-title">
            Avengers
        </h3>
        
        <p class="movie-description">
            In Marvel's The Avengers (2012), Earth's mightiest heroes—Iron Man, Captain America, the Hulk, Thor, Black Widow, and Hawkeye
            —are recruited by S.H.I.E.L.D. director Nick Fury. They must unite and learn to work as a team to stop 
            Thor’s adoptive brother, Loki, from subjugating humanity and taking over the world
        </p>
        
        <div class="movie-genres">
            
            <span class="genre-tag">
                Action
            </span>
            
            <span class="genre-tag">
                Sci-Fi
            </span>
            
            <span class="genre-tag">
                Adventure
            </span>
            
        </div>
        
    </div>
    
</div>