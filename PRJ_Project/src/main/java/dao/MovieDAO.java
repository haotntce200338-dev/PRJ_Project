/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mycompany.prj_project.db.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Movie;
import model.Genre;

public class MovieDAO extends DBConnection {

    //List
    public List<Movie> getList() {

        List<Movie> list = new ArrayList<>();

        try {

            String sql
                    = "select m.MovieID, "
                    + "m.Title, "
                    + "m.Description, "
                    + "m.Duration, "
                    + "m.ReleaseDate, "
                    + "m.Poster, "
                    + "m.Trailer, "
                    + "m.Status, "
                    + "g.GenreID, "
                    + "g.GenreName "
                    + "from Movie m "
                    + "join Genre g "
                    + "on m.GenreID = g.GenreID "
                    + "order by m.MovieID";

            PreparedStatement statement = this.getConnection().prepareStatement(sql);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                int movieID = rs.getInt(1);

                String title = rs.getString(2);

                String description = rs.getString(3);

                int duration = rs.getInt(4);

                String releaseDate = rs.getString(5);

                String poster = rs.getString(6);

                String trailer = rs.getString(7);

                String status = rs.getString(8);

                int genreID = rs.getInt(9);

                String genreName = rs.getString(10);

                Genre genre = new Genre(genreID, genreName);

                Movie movie = new Movie(
                        movieID,
                        title,
                        description,
                        duration,
                        releaseDate,
                        poster,
                        trailer,
                        status,
                        genre
                );

                list.add(movie);

            }

        } catch (SQLException ex) {

            Logger.getLogger(MovieDAO.class.getName())
                    .log(Level.SEVERE, null, ex);

        }

        return list;

    }

    //CREATE
    public int createMovie(Movie newMovie) {

        try {

            String sql
                    = "insert into Movie "
                    + "(Title, Description, Duration, ReleaseDate, Poster, Trailer, Status, GenreID) "
                    + "values (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = this.getConnection().prepareStatement(sql);

            statement.setString(1, newMovie.getTitle());

            statement.setString(2, newMovie.getDescription());

            statement.setInt(3, newMovie.getDuration());

            statement.setString(4, newMovie.getReleaseDate());

            statement.setString(5, newMovie.getPoster());

            statement.setString(6, newMovie.getTrailer());

            statement.setString(7, newMovie.getStatus());

            statement.setInt(8, newMovie.getGenre().getGenreID());

            return statement.executeUpdate();

        } catch (SQLException ex) {

            Logger.getLogger(MovieDAO.class.getName())
                    .log(Level.SEVERE, null, ex);

        }

        return 0;

    }

    //Get By Id
    public Movie getById(int id) {

        Movie movie = null;

        try {

            String sql
                    = "select m.MovieID, "
                    + "m.Title, "
                    + "m.Description, "
                    + "m.Duration, "
                    + "m.ReleaseDate, "
                    + "m.Poster, "
                    + "m.Trailer, "
                    + "m.Status, "
                    + "g.GenreID, "
                    + "g.GenreName "
                    + "from Movie m "
                    + "join Genre g "
                    + "on m.GenreID = g.GenreID "
                    + "where m.MovieID = ?";

            PreparedStatement statement
                    = this.getConnection()
                            .prepareStatement(sql);

            statement.setInt(1, id);

            ResultSet rs
                    = statement.executeQuery();

            if (rs.next()) {

                Integer movieID
                        = rs.getInt(1);

                String title
                        = rs.getString(2);

                String description
                        = rs.getString(3);

                Integer duration
                        = rs.getInt(4);

                String releaseDate
                        = rs.getString(5);

                String poster
                        = rs.getString(6);

                String trailer
                        = rs.getString(7);

                String status
                        = rs.getString(8);

                Integer genreID
                        = rs.getInt(9);

                String genreName
                        = rs.getString(10);

                Genre genre
                        = new Genre(
                                genreID,
                                genreName
                        );

                movie
                        = new Movie(
                                movieID,
                                title,
                                description,
                                duration,
                                releaseDate,
                                poster,
                                trailer,
                                status,
                                genre
                        );

            }

        } catch (SQLException ex) {

            Logger.getLogger(MovieDAO.class.getName())
                    .log(Level.SEVERE, null, ex);

        }

        return movie;

    }

    //Edit
    public int editMovie(Movie movie) {

        try {

            String sql
                    = "update Movie set "
                    + "Title=?, "
                    + "Description=?, "
                    + "Duration=?, "
                    + "ReleaseDate=?, "
                    + "Poster=?, "
                    + "Trailer=?, "
                    + "Status=?, "
                    + "GenreID=? "
                    + "where MovieID=?";

            PreparedStatement statement
                    = this.getConnection()
                            .prepareStatement(sql);

            statement.setString(
                    1,
                    movie.getTitle()
            );

            statement.setString(
                    2,
                    movie.getDescription()
            );

            statement.setInt(
                    3,
                    movie.getDuration()
            );

            statement.setString(
                    4,
                    movie.getReleaseDate()
            );

            statement.setString(
                    5,
                    movie.getPoster()
            );

            statement.setString(
                    6,
                    movie.getTrailer()
            );

            statement.setString(
                    7,
                    movie.getStatus()
            );

            statement.setInt(
                    8,
                    movie.getGenre().getGenreID()
            );

            statement.setInt(
                    9,
                    movie.getMovieID()
            );

            return statement.executeUpdate();

        } catch (SQLException ex) {

            Logger.getLogger(MovieDAO.class.getName())
                    .log(Level.SEVERE, null, ex);

        }

        return 0;

    }

    //Delete
    public int deleteMovie(int id) {

        try {

            // 1. Delete Review
            String sql1
                    = "DELETE FROM Review "
                    + "WHERE MovieID=?";

            PreparedStatement ps1
                    = getConnection()
                            .prepareStatement(sql1);

            ps1.setInt(1, id);

            ps1.executeUpdate();

            // 2. Delete Ticket
            String sql2
                    = "DELETE FROM Ticket "
                    + "WHERE ShowTimeID IN "
                    + "(SELECT ShowTimeID "
                    + "FROM ShowTime "
                    + "WHERE MovieID=?)";

            PreparedStatement ps2
                    = getConnection()
                            .prepareStatement(sql2);

            ps2.setInt(1, id);

            ps2.executeUpdate();

            // 3. Delete ShowTime
            String sql3
                    = "DELETE FROM ShowTime "
                    + "WHERE MovieID=?";

            PreparedStatement ps3
                    = getConnection()
                            .prepareStatement(sql3);

            ps3.setInt(1, id);

            ps3.executeUpdate();

            // 4. Delete Movie
            String sql4
                    = "DELETE FROM Movie "
                    + "WHERE MovieID=?";

            PreparedStatement ps4
                    = getConnection()
                            .prepareStatement(sql4);

            ps4.setInt(1, id);

            return ps4.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return 0;
    }
}
