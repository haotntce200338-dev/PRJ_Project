package controller;

import data.MovieData;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Movie;

@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        List<Movie> movies = MovieData.getMovies();

        request.setAttribute("nowShowing", movies);
        MovieData.getMovies();
        request.setAttribute("comingSoon", movies);
        MovieData.getMovies();
        request.setAttribute("topRated", movies);
        MovieData.getMovies();

        request.getRequestDispatcher("/WEB-INF/home/home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
