package com.mycompany.prj_project.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

    // Hàm này trả về một Connection (đường ống dẫn tới DB)
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection conn = null;

        // 1. Khai báo cho Java biết mình đang dùng Driver của SQL Server
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        // 2. Đường dẫn kết nối (Connection String)
        // Cú pháp: jdbc:sqlserver://[IP]:[Port];databaseName=[Tên DB]
        // Lưu ý: Các driver mới bắt buộc phải có encrypt=true;trustServerCertificate=true
        String url = "jdbc:sqlserver://localhost:1433;databaseName=MovieBookingDB;encrypt=true;trustServerCertificate=true";

        // 3. Thực hiện kết nối bằng tài khoản sa và mật khẩu của bạn
        String username = "sa";
        String password = "123456"; // Đổi thành mật khẩu thực tế bạn đặt ở Bước 2

        conn = DriverManager.getConnection(url, username, password);

        return conn;
    }
}
