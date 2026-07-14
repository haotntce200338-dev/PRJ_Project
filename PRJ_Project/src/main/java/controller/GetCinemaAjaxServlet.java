/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package cotroller;

import dao.CinemaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Cinema;

/**
 *
 * @author Admin
 */
@WebServlet(name = "GetCinemaAjaxServlet", urlPatterns = {"/GetCinemaAjax"})
public class GetCinemaAjaxServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=UTF-8");
        String regionCode = request.getParameter("regionCode");

        CinemaDAO dao = new CinemaDAO();
        List<Cinema> list = dao.getCinemasByRegion(regionCode);
        System.out.println("====== SỐ LƯỢNG RẠP TÌM THẤY CHO VÙNG " + regionCode + " LÀ: " + list.size());
        // tạo chuỗi JSON đơn giản
        try (PrintWriter out = response.getWriter()) {
            StringBuilder json = new StringBuilder("[");
            for (int i = 0; i < list.size(); i++) {
                Cinema c = list.get(i);
                json.append(String.format("{\"id\":%d, \"name\":\"%s\"}", c.getCinemaId(), c.getCinemaName()));
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
