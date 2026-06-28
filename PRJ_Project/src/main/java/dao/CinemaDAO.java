/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Admin
 */

import db.DBContext;
import model.Cinema;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CinemaDAO {
    public List<Cinema> getCinemasByRegion(String regionCode) {
    List<Cinema> list = new ArrayList<>();
    // 1. Đảm bảo tên bảng là Cinema và tên cột là region_code viết thường dấu gạch dưới
    String query = "SELECT * FROM Cinema WHERE TRIM(region_code) = ?";
    
    // 2. Sử dụng cấu trúc try-with-resources để tự động đóng kết nối, tránh tràn bộ nhớ
    try (Connection conn = new db.DBContext().getConnection(); 
         PreparedStatement ps = conn.prepareStatement(query)) {
        
        ps.setString(1, regionCode);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            // 3. ĐẢM BẢO: Đọc đúng tên cột trong Database mới tạo
            int id = rs.getInt("cinema_id");
            String name = rs.getString("cinema_name");
            String address = rs.getString("address");
            String rCode = rs.getString("region_code");
            
            // Tùy theo Constructor của class Cinema của ông, hãy truyền vào cho đúng thứ tự
            list.add(new Cinema(id, name, address, rCode));
        }
    } catch (Exception e) {
        System.out.println("====== LỖI KẾT NỐI AJAX TẠI CINEMADAO: " + e.getMessage());
        // QUAN TRỌNG: Phải có dòng này để nếu lỗi nó sẽ in ra màn hình Output của NetBeans cho mình nhìn
        e.printStackTrace(); 
    }
    return list;
}
}