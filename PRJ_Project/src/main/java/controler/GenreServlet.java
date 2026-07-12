/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler;

import dao.GenreDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Genre;

/**
 *
 * @author User
 */
@WebServlet(name = "GenreServlet", urlPatterns = {"/genre"})
public class GenreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        GenreDAO dao = new GenreDAO();

        String action = request.getParameter("action");

        if (action == null) {
            List<Genre> list = dao.getList();

            request.setAttribute("genreList", list);

            request.getRequestDispatcher("/WEB-INF/genre/list.jsp").forward(request, response);
        } else if (action.equals("create")) {
            request.getRequestDispatcher("/WEB-INF/genre/create.jsp").forward(request, response);
        } else if (action.equals("edit")) {

            int id = Integer.parseInt(request.getParameter("id"));

            Genre genre = dao.getGenreByID(id);

            request.setAttribute("genre", genre);

            request.getRequestDispatcher("WEB-INF/genre/edit.jsp")
                    .forward(request, response);

        } else if (action.equals("delete")) {

            int id = Integer.parseInt(request.getParameter("id"));

            dao.deleteGenre(id);

            response.sendRedirect("genre");

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        GenreDAO dao = new GenreDAO();

        if (action.equals("create")) {

            String genreName = request.getParameter("genreName");

            Genre g = new Genre();

            g.setGenreName(genreName);

            dao.createGenre(g);

            response.sendRedirect("genre");
        } else if (action.equals("edit")) {

            int genreID = Integer.parseInt(request.getParameter("genreID"));

            String genreName = request.getParameter("genreName");

            Genre genre = new Genre();

            genre.setGenreID(genreID);

            genre.setGenreName(genreName);

            dao.updateGenre(genre);

            response.sendRedirect("genre");

        }
    }

}
