/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.SeatDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Seat;

/**
 *
 * @author Admin
 */
@WebServlet(name = "SeatSelectionServlet", urlPatterns = {"/select-seat"})
public class SeatSelectionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1. Lấy dữ liệu ID lịch chiếu và ID phòng chiếu từ URL truyền sang
        String showtimeIdStr = request.getParameter("showtimeId");
        String roomIdStr = request.getParameter("roomId");

        // Các giá trị mặc định để test nếu chạy link không có tham số
        int showtimeId = 1;
        int roomId = 1;
        int ticketPrice = 60000;
        String movieTitle = "Phim Thử Nghiệm Giao Diện";

        // 2. Ép kiểu dữ liệu an toàn và giả lập giá vé theo dữ liệu thật
        try {
            if (showtimeIdStr != null && !showtimeIdStr.isEmpty()) {
                showtimeId = Integer.parseInt(showtimeIdStr);
            }
            if (roomIdStr != null && !roomIdStr.isEmpty()) {
                roomId = Integer.parseInt(roomIdStr);
            }

            // Giả lập logic đổi giá phim theo cơ chế cũ để test tính năng nhảy tiền động
            if (showtimeId == 101) {
                movieTitle = "Avengers: Endgame (IMAX)";
                ticketPrice = 90000;
            } else if (showtimeId == 102) {
                movieTitle = "Lật Mặt 7 (2D)";
                ticketPrice = 70000;
            } else if (showtimeId == 103) {
                movieTitle = "Doraemon (Suất Sớm)";
                ticketPrice = 50000;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace(); // In lỗi ra console nếu truyền chuỗi sai định dạng
        }

        // 3. GỌI DATABASE LẤY DANH SÁCH GHẾ THẬT ĐỔ LÊN MÀN HÌNH
        SeatDAO seatDAO = new SeatDAO();
        List<Seat> seatList = seatDAO.getSeatsByShowtime(showtimeId);

        // --- CẬP NHẬT BƯỚC 4: ĐẨY TOÀN BỘ DỮ LIỆU SANG TRANG JSP ĐỂ RENDER GIAO DIỆN ---
        request.setAttribute("seatList", seatList); 
        request.setAttribute("movieTitle", movieTitle);
        request.setAttribute("moviePrice", ticketPrice);

        // Forward trực tiếp sang file include của bạn
        request.getRequestDispatcher("/WEB-INF/include/booking-modal.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // 1. Kiểm tra đăng nhập để lấy thông tin khách hàng
        jakarta.servlet.http.HttpSession session = request.getSession();
        Object user = session.getAttribute("user");

        // Đoạn này giả lập ID người dùng là 1 nếu chưa làm chức năng đăng nhập để tránh lỗi NullPointer
        int userId = 1;

        // 2. Lấy dữ liệu parameters gửi lên từ AJAX ngoài giao diện
        String action = request.getParameter("action");
        String showtimeIdStr = request.getParameter("showtimeId");
        String seatIdStr = request.getParameter("seatId");
        String seatRow = request.getParameter("seatRow"); 
        String seatNoStr = request.getParameter("seatNo");   

        if (action == null || showtimeIdStr == null) {
            out.print("INVALID_PARAMETERS");
            return;
        }

        try {
            int showtimeId = Integer.parseInt(showtimeIdStr);
            SeatDAO seatDAO = new SeatDAO();
            int seatId = 0;
            
            if (seatIdStr != null && !seatIdStr.isEmpty()) {
                seatId = Integer.parseInt(seatIdStr);
            }

            // LOGIC FIX GHẾ TRỐNG (seatId == 0)
            if (seatId == 0 && seatRow != null && seatNoStr != null && !"release-multiple".equals(action)) {
                int seatNo = Integer.parseInt(seatNoStr);
                seatId = seatDAO.getSeatIdByLocation(showtimeId, seatRow, seatNo);
            }

            // --- CẬP NHẬT BƯỚC 4: PHÂN NHÁNH XỬ LÝ THEO HÀNH ĐỘNG CLICK CỦA NGƯỜI DÙNG ---
            if ("hold".equals(action)) {
                // Thực hiện giữ ghế (gọi hàm MERGE/INSERT trong SeatDAO)
                boolean success = seatDAO.holdSeat(showtimeId, seatId, userId);
                if (success) {
                    out.print("HOLD_SUCCESS");
                } else {
                    out.print("HOLD_FAILED"); // Ghế đã bị người khác nhanh tay giữ hoặc mua mất rồi
                }
                
            } else if ("release".equals(action)) {
                // Thực hiện hủy giữ ghế khi người dùng bỏ chọn đơn lẻ
                seatDAO.releaseSeat(showtimeId, seatId, userId);
                out.print("RELEASE_SUCCESS");
                
            } else if ("release-multiple".equals(action)) {
                // --- ĐÃ HOÀN THIỆN NHÁNH GIẢI PHÓNG HÀNG LOẠT ---
                String seatIdsStr = request.getParameter("seatIds");
                
                if (seatIdsStr != null && !seatIdsStr.trim().isEmpty()) {
                    // Cắt chuỗi các ID cách nhau bằng dấu phẩy
                    String[] seatIdArray = seatIdsStr.split(",");
                    
                    for (String idStr : seatIdArray) {
                        if (!idStr.trim().isEmpty()) {
                            int targetSeatId = Integer.parseInt(idStr.trim());
                            // Gọi hàm releaseSeat có sẵn để xóa trạng thái giữ chỗ của từng ghế
                            seatDAO.releaseSeat(showtimeId, targetSeatId, userId);
                        }
                    }
                    out.print("RELEASE_MULTIPLE_SUCCESS");
                } else {
                    out.print("NO_SEATS_TO_RELEASE");
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            out.print("ERROR");
        }
    }
}