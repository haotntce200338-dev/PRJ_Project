/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {
    private Connection conn;
    
    // ĐÃ SỬA: Đổi databaseName thành MovieBookingDB và thêm cấu hình bảo mật bypass mã hóa
    private final String DB_URL = "jdbc:sqlserver://127.0.0.1:1433;databaseName=MovieBookingDB;encrypt=true;trustServerCertificate=true;";
    private final String DB_USER = "sa";
    
    // CHÚ Ý: Điền đúng mật khẩu sa trên máy ông vào đây (Ví dụ: "123" hoặc "123456")
    private final String DB_PWD = "123456"; 

    public DBContext() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
        } catch (ClassNotFoundException | SQLException ex) {
            // Dòng này của thầy sẽ in chi tiết lỗi ra tab Tomcat nếu sai pass hoặc sai tên DB
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        return conn;
    }
}