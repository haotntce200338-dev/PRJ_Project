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
    
    public List<Genre> getList(){
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
}
