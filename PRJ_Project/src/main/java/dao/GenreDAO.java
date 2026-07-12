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
import model.Genre;

/**
 *
 * @author User
 */
public class GenreDAO extends DBConnection {

    //Read - lay danh sach Genre
    public List<Genre> getList() {
        List<Genre> list = new ArrayList<>();

        String sql = "SELECT * FROM Genre";

        try {

            PreparedStatement ps = getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Genre g = new Genre();

                g.setGenreID(rs.getInt("GenreID"));

                g.setGenreName(rs.getString("GenreName"));

                list.add(g);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return list;
    }

    //Create
    public int createGenre(Genre g) {

        try {

            String sql = "INSERT INTO Genre(GenreName) VALUES(?)";

            PreparedStatement ps = getConnection().prepareStatement(sql);

            ps.setString(1, g.getGenreName());

            return ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return 0;
    }

    //Read One
    public Genre getGenreByID(int id) {

        String sql = "SELECT * FROM Genre WHERE GenreID = ?";

        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Genre g = new Genre();
                g.setGenreID(rs.getInt("GenreID"));
                g.setGenreName(rs.getString("GenreName"));
                return g;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //Update
    // UPDATE
    public int updateGenre(Genre genre) {

        String sql = "UPDATE Genre SET GenreName = ? WHERE GenreID = ?";

        try {

            PreparedStatement ps = getConnection().prepareStatement(sql);

            ps.setString(1, genre.getGenreName());

            ps.setInt(2, genre.getGenreID());

            return ps.executeUpdate();

        } catch (SQLException ex) {

            Logger.getLogger(GenreDAO.class.getName())
                    .log(Level.SEVERE, null, ex);

        }

        return 0;
    }

    // DELETE
    public int deleteGenre(int id) {

        String sql = "DELETE FROM Genre WHERE GenreID = ?";

        try {

            PreparedStatement ps = getConnection().prepareStatement(sql);

            ps.setInt(1, id);

            return ps.executeUpdate();

        } catch (SQLException ex) {

            Logger.getLogger(GenreDAO.class.getName())
                    .log(Level.SEVERE, null, ex);

        }

        return 0;
    }

}
