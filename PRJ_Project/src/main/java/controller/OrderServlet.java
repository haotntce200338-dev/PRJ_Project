/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Order;
import model.Ticket;
import model.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "OrderServlet", urlPatterns = {"/order"})
public class OrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      HttpSession session = request.getSession();
        User user = (User) session.getAttribute("LOGIN_USER");
        if (user == null) {
            user = new User();
            
            // ⚠️ QUAN TRỌNG: Hãy đổi số 1 này thành một user_id có thật trong bảng dbo.User của ông nhé!
            user.setUserId(1); 
            
            // Giả định ngày sinh suông để tránh lỗi hệ thống khác nếu có kiểm tra tuổi
            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.set(2000, java.util.Calendar.JANUARY, 1);
            user.setBirthDate(cal.getTime()); 
            
            // Lưu ngược lại vào session để các trang sau xài chung
            session.setAttribute("LOGIN_USER", user);
        }
        
        // Đọc dữ liệu từ form ẩn của payment.jsp gửi qua
        String showtimeIdStr = request.getParameter("showtimeId");
        String seatIdsString = request.getParameter("seatIdsString"); 
        String totalAmountStr = request.getParameter("totalAmount");
        String paymentMethod = request.getParameter("paymentMethod");

        // ️ THAY ĐỔI: Nếu thiếu dữ liệu, in thẳng lỗi ra màn hình để kiểm tra form, CẤM quay lại sơ đồ ghế
        if (showtimeIdStr == null || showtimeIdStr.trim().isEmpty() 
                || seatIdsString == null || seatIdsString.trim().isEmpty() 
                || totalAmountStr == null || totalAmountStr.trim().isEmpty()) {
            
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<h2> LỖI TRUYỀN DỮ LIỆU THANH TOÁN </h2>");
                out.println("<p>Hệ thống phát hiện dữ liệu từ trang thanh toán gửi qua bị thiếu:</p>");
                out.println("<ul>");
                out.println("<li><b>Mã suất chiếu (showtimeId):</b> " + showtimeIdStr + "</li>");
                out.println("<li><b>Chuỗi ID ghế (seatIdsString):</b> " + seatIdsString + "</li>");
                out.println("<li><b>Tổng số tiền (totalAmount):</b> " + totalAmountStr + "</li>");
                out.println("</ul>");
                out.println("<p> Hãy kiểm tra xem file payment.jsp đã có đủ 3 thẻ input hidden tương ứng chưa.</p>");
                out.println("<a href='javascript:history.back()'> Quay lại trang thanh toán</a>");
            }
            return;
        }

        // Ép kiểu dữ liệu sau khi đã chắc chắn không bị null/empty
        int showtimeId = Integer.parseInt(showtimeIdStr.trim());
        double totalAmount = Double.parseDouble(totalAmountStr.trim());
        String[] selectedSeatIds = seatIdsString.split(","); 

        // Tính toán giá vé trung bình trên mỗi ghế để lưu vào bảng Ticket
        double pricePerSeat = totalAmount / selectedSeatIds.length; 
        
        List<Ticket> tickets = new ArrayList<>();
        for (String seatIdStr : selectedSeatIds) {
            int seatId = Integer.parseInt(seatIdStr.trim());
            tickets.add(new Ticket(showtimeId, seatId, pricePerSeat));
        }

        // Đóng gói dữ liệu vào đối tượng Order
        Order order = new Order();
        order.setUserId(1); 
        order.setTotalAmount(totalAmount);
        
        if ("COD".equals(paymentMethod)) {
            order.setStatus("Completed");
        } else {
            order.setStatus("Pending"); 
        }

        // 4. Lưu vào Database thông qua DAO
        OrderDAO orderDAO = new OrderDAO();
        int generatedOrderId = orderDAO.createOrderWithTickets(order, tickets);

        if (generatedOrderId != -1) {
            // ĐẶT VÉ HOÀN TẤT THÀNH CÔNG: Đẩy thẳng sang trang thông báo hóa đơn chính thức
            session.removeAttribute("SELECTED_SHOWTIME_ID");
            
            request.setAttribute("ORDER_ID", generatedOrderId);
            request.setAttribute("PAYMENT_METHOD", paymentMethod);
            request.getRequestDispatcher("/WEB-INF/view/order_success.jsp").forward(request, response);
        } else {
            // Nếu lỗi SQL/Database xảy ra
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<h2> LỖI HỆ THỐNG DATABASE </h2>");
                out.println("<p>Hàm lưu hóa đơn <b>createOrderWithTickets</b> trong <b>OrderDAO</b> trả về -1.</p>");
                out.println("<p>Vui lòng kiểm tra lại câu lệnh SQL INSERT hoặc kết nối cơ sở dữ liệu của bạn!</p>");
                out.println("<a href='javascript:history.back()'> Quay lại</a>");
            }
        }
    }
}
