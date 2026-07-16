/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import db.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import model.Order;
import model.Ticket;
/**
 *
 * @author Admin
 */
public class OrderDAO extends DBContext{
    public int createOrderWithTickets(Order order, List<Ticket> tickets) {
        // ⚠️ Hãy kiểm tra lại chính xác tên bảng trong DB của ông xem có chữ 's' hay không nhé!
        String insertOrder = "INSERT INTO Orders(user_id, total_amount, status) VALUES(?,?,?)";
        String insertTicket = "INSERT INTO Ticket(order_id, showtime_id, seat_id, price) VALUES(?,?,?,?)";
        
        Connection conn = null; // Khai báo ngoài để gọi được rollback trong khối catch
        try {
            conn = getConnection();
            conn.setAutoCommit(false); // Bắt đầu Transaction
            
            // 1. Tạo Order mới
            PreparedStatement psOrder = conn.prepareStatement(insertOrder, Statement.RETURN_GENERATED_KEYS);
            psOrder.setInt(1, order.getUserId());
            psOrder.setDouble(2, order.getTotalAmount());
            psOrder.setString(3, order.getStatus());
            psOrder.executeUpdate();
            
            // Lấy ra order_id vừa tạo
            ResultSet rs = psOrder.getGeneratedKeys();
            int orderId = -1;
            if (rs.next()) {
                orderId = rs.getInt(1);
            }
            
            // 2. Nếu tạo Order thành công, tiến hành lưu Ticket
            if (orderId != -1 && tickets != null && !tickets.isEmpty()) {
                PreparedStatement psTicket = conn.prepareStatement(insertTicket);
                for (Ticket t : tickets) {
                    psTicket.setInt(1, orderId);
                    psTicket.setInt(2, t.getShowtimeId());
                    psTicket.setInt(3, t.getSeatId());
                    psTicket.setDouble(4, t.getPrice());
                    psTicket.addBatch(); 
                }
                psTicket.executeBatch(); // Thực thi loạt lệnh INSERT
            }
            
            conn.commit(); // Thành công hết thì hoàn tất transaction
            return orderId;
            
        } catch (Exception e) {
            e.printStackTrace(); // In lỗi chi tiết ra console (ví dụ: lỗi khóa ngoại, lỗi tên bảng)
            if (conn != null) {
                try {
                    conn.rollback(); // 🛠️ QUAN TRỌNG: Quay xe hủy toàn bộ nếu có bất kỳ lỗi nào xảy ra
                    System.out.println("⚠ Transaction đã được Rollback thành công!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close(); // Đảm bảo kết nối luôn luôn được đóng sạch sẽ
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return -1;
    }
}
