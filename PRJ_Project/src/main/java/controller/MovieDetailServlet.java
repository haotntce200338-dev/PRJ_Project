package controller;

import data.MovieData;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Movie;

@WebServlet(name = "MovieDetailServlet", urlPatterns = {"/movie-detail"})
public class MovieDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Movie movie = MovieData.getMovieById(id);

        MovieData.getMovies();

        request.setAttribute("movie", movie);
        request.getRequestDispatcher("/WEB-INF/movie/movie-detail.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
