package com.mycompany.prj_project.dao;

import com.mycompany.prj_project.dto.UserDTO;
import com.mycompany.prj_project.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public UserDTO checkLogin(String userID, String password) throws SQLException {
        UserDTO user = null; // Khởi tạo object rỗng, nếu sai pass thì nó vẫn là null
        
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            // Bước 1: Mở kết nối tới SQL Server
            conn = DBUtils.getConnection();
            
            if (conn != null) {
                // Bước 2: Viết câu lệnh SQL. 
                // CHÚ Ý: Dùng dấu '?' để chống bị hack (SQL Injection)
                String sql = "SELECT fullName, roleID FROM Users "
                           + "WHERE userID = ? AND password = ? AND status = 1";
                
                // Bước 3: Đưa câu lệnh vào PreparedStatement và truyền tham số
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);   // Thay thế dấu '?' thứ nhất
                stm.setString(2, password); // Thay thế dấu '?' thứ hai
                
                // Bước 4: Thực thi câu lệnh (Bấm F5 trong SQL Server) và nhận bảng kết quả
                rs = stm.executeQuery();
                
                // Bước 5: Đọc dữ liệu. 
                if (rs.next()) { // Nếu tìm thấy một dòng thỏa mãn
                    String fullName = rs.getString("fullName"); // Lấy cột fullName
                    int roleID = rs.getInt("roleID");           // Lấy cột roleID
                    
                    // Gom dữ liệu lấy được vào đối tượng UserDTO
                    // Các thuộc tính không cần thiết (như password) có thể để rỗng ""
                    user = new UserDTO(userID, "", fullName, "", roleID, true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Bước 6: LUÔN LUÔN ĐÓNG KẾT NỐI (rất quan trọng)
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        
        // Trả kết quả về cho LoginServlet
        return user;
    }
}