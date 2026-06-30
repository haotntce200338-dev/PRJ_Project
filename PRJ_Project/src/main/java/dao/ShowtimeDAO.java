/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Admin
 */
import model.Showtime;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import db.DBContext;
import model.Showtime;

public class ShowtimeDAO {

    public List<Showtime> getShowtimesByFilter(int movieId, int cinemaId, String date) {
        List<Showtime> list = new ArrayList<>();
        String query = "SELECT * FROM Showtime WHERE movie_id = ? AND cinema_id = ? AND show_date = ? AND status = 1";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, movieId);
            ps.setInt(2, cinemaId);
            ps.setString(3, date);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Showtime st = new Showtime();
                st.setShowtimeId(rs.getInt("showtime_id"));
                st.setMovieId(rs.getInt("movie_id"));
                st.setCinemaId(rs.getInt("cinema_id"));
                st.setShowDate(rs.getDate("show_date"));
                st.setStartTime(rs.getTime("start_time"));
                list.add(st);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
