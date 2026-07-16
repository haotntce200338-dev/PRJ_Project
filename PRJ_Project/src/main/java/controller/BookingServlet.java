/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CinemaDAO;
import dao.ShowtimeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import model.Cinema;
import model.Showtime;
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
        String movieId = request.getParameter("movieId");
        if (movieId == null || movieId.trim().isEmpty()) {
            movieId = "1"; // Backup mặc định nếu không nhận được movieId
        }
        request.setAttribute("movieId", movieId);

        // --- BƯỚC 1: HIỂN THỊ TRANG CHỌN SUẤT CHIẾU VÀ XỬ LÝ LỌC ---
        if (action == null || action.equals("select_showtime")) {
            String regionCode = request.getParameter("regionCode");
            String cinemaIdStr = request.getParameter("cinemaId");
            String showDate = request.getParameter("showDate");

            CinemaDAO cinemaDAO = new CinemaDAO();
            ShowtimeDAO showtimeDAO = new ShowtimeDAO();

            // 1. Nếu có chọn Khu vực -> Lấy danh sách rạp tương ứng
            if (regionCode != null && !regionCode.trim().isEmpty()) {
                List<Cinema> cinemaList = cinemaDAO.getCinemasByRegion(regionCode);
                request.setAttribute("cinemaList", cinemaList);
                request.setAttribute("selectedRegion", regionCode); // Giữ lại vùng đã chọn
            }

            // 2. Nếu đã chọn cả Rạp và Ngày -> Lấy danh sách suất chiếu khả dụng
            if (cinemaIdStr != null && !cinemaIdStr.trim().isEmpty() 
                    && showDate != null && !showDate.trim().isEmpty()) {
                
                int cinemaId = Integer.parseInt(cinemaIdStr);
                int mId = Integer.parseInt(movieId);
                
                List<Showtime> showtimeList = showtimeDAO.getShowtimesByFilter(mId, cinemaId, showDate);
                
                request.setAttribute("showtimeList", showtimeList);
                request.setAttribute("selectedCinemaId", cinemaId); // Giữ lại rạp đã chọn
                request.setAttribute("selectedShowDate", showDate); // Giữ lại ngày đã chọn
            }

            // Chuyển tiếp sang trang hiển thị
            request.getRequestDispatcher("/WEB-INF/view/booking_showtime.jsp").forward(request, response);
        } 
        
        // --- BƯỚC 2: XÁC NHẬN SUẤT CHIẾU VÀ KIỂM TRA TUỔI ---
        else if (action.equals("confirm_showtime")) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user"); 
            String showtimeId = request.getParameter("showtimeId");

            // Giả lập người dùng đăng nhập nếu chưa có
            if (user == null) {
                user = new User();
                Calendar cal = Calendar.getInstance();
                cal.set(2000, Calendar.JANUARY, 1);
                user.setBirthDate(cal.getTime());
                session.setAttribute("user", user);
            }

            // Tính tuổi người dùng
            int userAge = 20; // Mặc định phòng hờ
            if (user.getBirthDate() != null) {
                Calendar birth = Calendar.getInstance();
                birth.setTime(user.getBirthDate());
                Calendar today = Calendar.getInstance();
                userAge = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
                if (today.get(Calendar.DAY_OF_YEAR) < birth.get(Calendar.DAY_OF_YEAR)) {
                    userAge--;
                }
            }

            int movieAgeRestriction = 18; // Lấy từ MovieDAO tương ứng
            if (userAge < movieAgeRestriction) {
                request.setAttribute("ERROR_MSG", "Tài khoản của bạn chưa đủ tuổi để xem phim này!");
                // Nếu lỗi, quay lại trang chọn suất chiếu và tải lại các thông tin lọc trước đó
                request.getRequestDispatcher("booking?action=select_showtime").forward(request, response);
                return;
            }

            // Hợp lệ thì tiến hành lưu suất chiếu vào session và chuyển sang chọn ghế
            session.setAttribute("SELECTED_SHOWTIME_ID", showtimeId);
            response.sendRedirect(request.getContextPath() + "/select-seat");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}