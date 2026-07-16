/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "PaymentServlet", urlPatterns = {"/payment"})
public class PaymentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // 1. Lấy thông tin từ URL được truyền từ trang chọn ghế sang
        String showtimeId = request.getParameter("showtimeId"); // 🛠️ THÊM DÒNG NÀY để bốc showtimeId từ URL
        String seats = request.getParameter("seats");
        String seatIds = request.getParameter("seatIds");
        String adult = request.getParameter("adult");
        String student = request.getParameter("student");
        String amount = request.getParameter("amount");

        // Nếu trên URL không có showtimeId, thử cứu cánh bằng cách bốc từ Session cũ
        if (showtimeId == null || showtimeId.trim().isEmpty()) {
            Object sessionShowtime = request.getSession().getAttribute("SELECTED_SHOWTIME_ID");
            if (sessionShowtime != null) {
                showtimeId = sessionShowtime.toString();
            }
        }

        // 2. Đẩy các thông tin này vào request attribute để hiển thị lên giao diện thanh toán
        request.setAttribute("showtimeId", showtimeId); // 🛠️ THÊM DÒNG NÀY để truyền sang file payment.jsp
        request.setAttribute("seats", seats);
        request.setAttribute("seatIds", seatIds);
        request.setAttribute("adult", adult);
        request.setAttribute("student", student);
        request.setAttribute("amount", amount);

        // 3. Chuyển hướng sang giao diện trang payment.jsp
        request.getRequestDispatcher("/WEB-INF/view/payment.jsp").forward(request, response);
    }
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
    }

    
}

