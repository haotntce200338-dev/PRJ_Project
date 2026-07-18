package controller;

import data.MovieData;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Movie;

@WebServlet(name = "SearchServlet", urlPatterns = {"/search"})
public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = request.getParameter("keyword");

        if (keyword == null) {
            keyword = "";
        }

        keyword = keyword.trim().toLowerCase();

        List<Movie> allMovies = MovieData.getMovies();

        List<Movie> result = new ArrayList<>();

        for (Movie m : allMovies) {

            if (m.getTitle().toLowerCase().contains(keyword)
                    || m.getGenres().toLowerCase().contains(keyword)
                    || m.getDescription().toLowerCase().contains(keyword)
                    || m.getDirector().toLowerCase().contains(keyword)
                    || m.getCast().toLowerCase().contains(keyword)
                    || m.getOverview().toLowerCase().contains(keyword)) {

                result.add(m);

            }
        }

        request.setAttribute("keyword", keyword);
        request.setAttribute("movies", result);

        request.getRequestDispatcher("/WEB-INF/search/search.jsp").forward(request, response);
    }

    private boolean contains(String text, String keyword) {
        return text != null
                && text.toLowerCase().contains(keyword);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
