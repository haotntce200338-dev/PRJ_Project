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

/**
 *
 * @author Admin
 */
@WebServlet(name = "SeatSelectionServlet", urlPatterns = {"/select-seat"})
public class SeatSelectionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Lấy ID lịch chiếu (Showtime ID) hoặc Movie ID từ trang trước truyền sang
        String showtimeIdStr = request.getParameter("showtimeId");

        // Mặc định ban đầu để test tính năng nhảy tiền động
        int ticketPrice = 60000;
        String movieTitle = "Phim Thử Nghiệm Giao Diện";

        //Giả lập lấy giá vé khác nhau theo từng suất chiếu/phim để test
        if (showtimeIdStr != null) {
            try {
                int showtimeId = Integer.parseInt(showtimeIdStr);
                if (showtimeId == 101) {
                    movieTitle = "Avengers: Endgame (IMAX)";
                    ticketPrice = 90000; // Suất chiếu IMAX giá cao hơn
                } else if (showtimeId == 102) {
                    movieTitle = "Lật Mặt 7 (2D)";
                    ticketPrice = 70000; // Suất 2D thường
                } else if (showtimeId == 103) {
                    movieTitle = "Doraemon (Suất Sớm)";
                    ticketPrice = 50000; // Suất ưu đãi trẻ em
                }
            } catch (NumberFormatException e) {
       
            }
        }

        
        request.setAttribute("movieTitle", movieTitle);
        request.setAttribute("moviePrice", ticketPrice);


       
        request.getRequestDispatcher("view-movie.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
