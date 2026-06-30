/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package cotroller;

import dao.ShowtimeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Showtime;

/**
 *
 * @author Admin
 */
@WebServlet(name = "GetShowtimeAjaxServlet", urlPatterns = {"/GetShowtimeAjax"})
public class GetShowtimeAjaxServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        // Lấy các tham số lọc từ giao diện gửi lên
        int movieId = Integer.parseInt(request.getParameter("movieId"));
        int cinemaId = Integer.parseInt(request.getParameter("cinemaId"));
        String showDate = request.getParameter("showDate");

        ShowtimeDAO dao = new ShowtimeDAO();
        List<Showtime> list = dao.getShowtimesByFilter(movieId, cinemaId, showDate);

        // Biến đổi danh sách Object thành chuỗi JSON trả về cho Client
        try (PrintWriter out = response.getWriter()) {
            StringBuilder json = new StringBuilder("[");
            for (int i = 0; i < list.size(); i++) {
                Showtime st = list.get(i);
                // Lấy chuỗi hh:mm từ start_time
                String timeStr = st.getStartTime().toString().substring(0, 5);

                json.append(String.format("{\"showtimeId\":%d, \"startTime\":\"%s\"}",
                        st.getShowtimeId(), timeStr));
                if (i < list.size() - 1) {
                    json.append(",");
                }
            }
            json.append("]");
            out.print(json.toString());
            out.flush();
        }
    }
}
