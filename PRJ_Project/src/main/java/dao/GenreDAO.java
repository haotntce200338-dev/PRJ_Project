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
        
        try{
            String sql = "select * from Genre "
                    + "order by GenreID";
            
            PreparedStatement statement = this.getConnection().prepareStatement(sql);
            
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()){
                
                int id = rs.getInt("GenreID");
                
                String name = rs.getString("GenreName");
                
                Genre genre = new Genre(id,name);
                
                list.add(genre);
            }
            
        }catch (SQLException ex){
            
            Logger.getLogger(GenreDAO.class.getName()).log(Level.SEVERE,null,ex);
        }
        return list;
    }
}
