package controller;

import data.MovieData;
import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;
import model.Movie;

@WebServlet(name = "SuggestServlet", urlPatterns = {"/suggest"})
public class SuggestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        String keyword = request.getParameter("keyword");

        if (keyword == null) {
            keyword = "";
        }

        keyword = keyword.toLowerCase().trim();

        List<Movie> movies = MovieData.getMovies();

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        for (Movie m : movies) {

            if (m.getTitle().toLowerCase().contains(keyword)) {

                out.println(
                        "<div class='suggest-item' "
                        + "onclick=\"location.href='"
                        + request.getContextPath()
                        + "/movie-detail?id="
                        + m.getMovieID()
                        + "'\">"
                        + "<img src='"
                        + request.getContextPath()
                        + "/"
                        + m.getPoster()
                        + "'>"
                        + "<span>"
                        + m.getTitle()
                        + "</span>"
                        + "</div>");

            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {
    }

}
