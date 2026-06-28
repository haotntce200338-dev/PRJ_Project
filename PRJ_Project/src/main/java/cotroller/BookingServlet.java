/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package cotroller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "BookingServlet", urlPatterns = {"/booking"})
public class BookingServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      String action = request.getParameter("action");
        
        // Bước 1: Hiển thị trang chọn suất chiếu
        if (action == null || action.equals("select_showtime")) {
            // Giả định nhóm đã truyền hoặc lấy được movieId
            String movieId = request.getParameter("movieId");
            request.setAttribute("movieId", movieId);
            
            // Gọi các DAO để lấy danh sách Khu vực / Rạp đổ vào giao diện (nếu cần load ban đầu)
            
            request.getRequestDispatcher("/WEB-INF/view/booking_showtime.jsp").forward(request, response);
        } 
        // Bước 2: Khi người dùng bấm chọn một Suất Chiếu cụ thể để đi tiếp
        else if (action.equals("confirm_showtime")) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("LOGIN_USER"); // Key user khi đăng nhập thành công
            
            String showtimeId = request.getParameter("showtimeId");
            String movieId = request.getParameter("movieId");
            
            // 1. Kiểm tra Đăng Nhập
            if (user == null) {
                // Lưu lại mong muốn của user vào session để sau khi login thì quay lại đúng chỗ này
                session.setAttribute("REDIRECT_URL", "booking?action=confirm_showtime&showtimeId=" + showtimeId + "&movieId=" + movieId);
                response.sendRedirect("login.jsp"); // Chuyển hướng sang trang đăng nhập của nhóm
                return;
            }
            
            // 2. Kiểm tra Độ tuổi (Logic Backend bổ trợ cho Frontend)
            // Giả định thực tế phim yêu cầu 18 tuổi, lấy tuổi từ object user ra so sánh
            int userAge = user.getAge(); 
            int movieAgeRestriction = 18; // Logic này ông có thể lấy từ MovieDAO dựa theo movieId
            
            if (userAge < movieAgeRestriction) {
                request.setAttribute("ERROR_MSG", "Tài khoản của bạn chưa đủ tuổi để xem phim này!");
                request.getRequestDispatcher("/WEB-INF/view/booking_showtime.jsp").forward(request, response);
                return;
            }
            
            // 3. Nếu mọi thứ hợp lệ -> Chuyển sang trang chọn ghế (Task phối hợp với Người 4)
            session.setAttribute("SELECTED_SHOWTIME_ID", showtimeId);
            response.sendRedirect("booking_seat.jsp"); 
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       doGet(request, response);
    }

}
