/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler;

import dao.GenreDAO;
import model.Genre;
import dao.MovieDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Movie;

/**
 *
 * @author User
 */
@WebServlet(name = "MovieServlet", urlPatterns = {"/home"})
public class MovieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {

            MovieDAO dao = new MovieDAO();

            List<Movie> list = dao.getList();

            request.setAttribute("movieList", list);

            request.getRequestDispatcher("WEB-INF/home/list.jsp")
                    .forward(request, response);

        } else if (action.equals("create")) {

            GenreDAO dao = new GenreDAO();

            List<Genre> genreList = dao.getList();

            request.setAttribute("genreList", dao.getList());

            request.getRequestDispatcher("WEB-INF/home/create.jsp")
                    .forward(request, response);

        } else if (action.equals("edit")) {
            MovieDAO dao = new MovieDAO();

            int id = Integer.parseInt(request.getParameter("id"));

            Movie movie = dao.getById(id);

            GenreDAO gdao = new GenreDAO();

            request.setAttribute("movie", movie);

            request.setAttribute("genreList", gdao.getList());

            request.getRequestDispatcher("WEB-INF/home/edit.jsp")
                    .forward(request, response);
        } else if (action.equals("delete")) {

            int id = Integer.parseInt(request.getParameter("id"));

            MovieDAO dao = new MovieDAO();

            dao.deleteMovie(id);

            response.sendRedirect("home");

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action.equals("create")) {
            // Code create của bạn

            String title = request.getParameter("title");
            String description = request.getParameter("description");
            int duration = Integer.parseInt(request.getParameter("duration"));
            String releaseDate = request.getParameter("releaseDate");
            String poster = request.getParameter("poster");
            String trailer = request.getParameter("trailer");
            String status = request.getParameter("status");
            int genreID = Integer.parseInt(request.getParameter("genreID"));

            Movie movie = new Movie();

            movie.setTitle(title);
            movie.setDescription(description);
            movie.setDuration(duration);
            movie.setReleaseDate(releaseDate);
            movie.setPoster(poster);
            movie.setTrailer(trailer);
            movie.setStatus(status);

            Genre genre = new Genre();
            genre.setGenreID(genreID);

            movie.setGenre(genre);

            MovieDAO dao = new MovieDAO();

            dao.createMovie(movie);

            response.sendRedirect("home");
        } else if (action.equals("edit")) {

            int movieID = Integer.parseInt(request.getParameter("movieID"));

            String title = request.getParameter("title");
            String description = request.getParameter("description");
            int duration = Integer.parseInt(request.getParameter("duration"));
            String releaseDate = request.getParameter("releaseDate");
            String poster = request.getParameter("poster");
            String trailer = request.getParameter("trailer");
            String status = request.getParameter("status");
            int genreID = Integer.parseInt(request.getParameter("genreID"));

            Genre genre = new Genre();
            genre.setGenreID(genreID);

            Movie movie = new Movie();

            movie.setMovieID(movieID);
            movie.setTitle(title);
            movie.setDescription(description);
            movie.setDuration(duration);
            movie.setReleaseDate(releaseDate);
            movie.setPoster(poster);
            movie.setTrailer(trailer);
            movie.setStatus(status);
            movie.setGenre(genre);

            MovieDAO dao = new MovieDAO();

            dao.updateMovie(movie);

            response.sendRedirect("home");
        }
    }
}
