package com.mycompany.prj_project.controller;

import com.mycompany.prj_project.dao.UserDAO;
import com.mycompany.prj_project.dto.UserDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

// Dòng này định nghĩa đường dẫn. Khi form HTML gọi action="LoginServlet", nó sẽ chạy vào đây.
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // --- NHỊP 1: HỨNG DỮ LIỆU TỪ TRANG LOGIN.JSP ---
        // Tên trong ngoặc kép phải khớp 100% với thuộc tính name="..." của thẻ <input> bên HTML
        String userID = request.getParameter("txtUserID");
        String password = request.getParameter("txtPassword");
        
        try {
            // --- NHỊP 2: GỌI DAO ĐỂ KIỂM TRA DATABASE ---
            UserDAO dao = new UserDAO();
            UserDTO loginUser = dao.checkLogin(userID, password); 
            
            // --- NHỊP 3 & 4: XỬ LÝ KẾT QUẢ VÀ ĐIỀU HƯỚNG ---
            if (loginUser != null) {
                // TRƯỜNG HỢP 1: ĐĂNG NHẬP THÀNH CÔNG
                
                // Khởi tạo Session và cấp phát "thẻ" ghi nhớ thông tin user
                HttpSession session = request.getSession();
                session.setAttribute("LOGIN_USER", loginUser); 
                
                // Dùng sendRedirect để đổi URL trên trình duyệt và nhảy sang trang chủ
                response.sendRedirect("home.jsp"); 
                
            } else {
                // TRƯỜNG HỢP 2: ĐĂNG NHẬP THẤT BẠI
                
                // Lưu một câu thông báo lỗi vào request
                request.setAttribute("ERROR_MESSAGE", "Tài khoản hoặc mật khẩu không đúng!");
                
                // Dùng getRequestDispatcher để giữ nguyên URL trang login và ném thông báo lỗi ra giao diện
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            
        } catch (Exception e) {
            // In lỗi ra màn hình Output của NetBeans để dễ debug
            log("Error at LoginServlet: " + e.toString());
            // Tránh lỗi trắng trang khi crash Database
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi hệ thống khi đăng nhập!");
        }
    }
}