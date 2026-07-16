package dao;

import db.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Seat;

public class SeatDAO extends DBContext {

    // 1. Hàm lấy danh sách ghế kèm trạng thái động (SỬA LỖI LẶP GHẾ DO INNER JOIN SHOWTIME)
    public List<Seat> getSeatsByShowtime(int showtimeId) {
        List<Seat> list = new ArrayList<>();
    String sql = "SELECT \n"
            + "    s.seat_id,\n"
            + "    s.room_id,\n"
            + "    s.seat_row,\n" // Giữ nguyên vì DB là VARCHAR
            + "    s.seat_number,\n"
            + "    s.seat_type,\n"
            + "    CASE \n"
            + "        WHEN t.ticket_id IS NOT NULL THEN 'OCCUPIED'\n"
            + "        WHEN l.seat_id IS NOT NULL AND l.locked_until > GETDATE() THEN 'HOLD'\n"
            + "        ELSE 'AVAILABLE'\n"
            + "    END AS seat_status,\n"
            + "    l.locked_by_user\n"
            + "FROM Seat s\n"
            + "INNER JOIN Showtime st ON s.room_id = st.room_id\n"
            + "LEFT JOIN Ticket t ON s.seat_id = t.seat_id AND t.showtime_id = st.showtime_id\n"
            + "LEFT JOIN Showtime_Seat_Lock l ON s.seat_id = l.seat_id AND l.showtime_id = st.showtime_id\n"
            + "WHERE st.showtime_id = ?"; // Khóa chặt theo đúng ID suất chiếu trên giao diện

    try {
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, showtimeId);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Seat s = new Seat();
            s.setSeatId(rs.getInt("seat_id"));
            s.setRoomId(rs.getInt("room_id"));
            s.setSeatRow(rs.getString("seat_row"));
            s.setSeatNumber(rs.getInt("seat_number"));
            s.setSeatType(rs.getString("seat_type"));
            s.setStatus(rs.getString("seat_status")); 
            list.add(s);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
    }

    // 2. Hàm giữ ghế tạm thời (BỎ LỆNH MERGE - DÙNG KIỂM TRA TRUYỀN THỐNG TRÁNH LỖI DRIVER JDBC)
    public boolean holdSeat(int showtimeId, int seatId, int userId) {
        // Bước 2.1: Check xem ghế này hiện tại có ai đang giữ hợp lệ hoặc đã mua chưa
        String checkSql = "SELECT locked_by_user, locked_until FROM Showtime_Seat_Lock "
                        + "WHERE showtime_id = ? AND seat_id = ?";
        
        String insertSql = "INSERT INTO Showtime_Seat_Lock (showtime_id, seat_id, locked_by_user, locked_until) "
                         + "VALUES (?, ?, ?, DATEADD(minute, 5, GETDATE()))";
        
        String updateSql = "UPDATE Showtime_Seat_Lock SET locked_by_user = ?, locked_until = DATEADD(minute, 5, GETDATE()) "
                         + "WHERE showtime_id = ? AND seat_id = ?";
                         
        try {
            // Kiểm tra trạng thái hiện tại trong DB
            PreparedStatement cp = conn.prepareStatement(checkSql);
            cp.setInt(1, showtimeId);
            cp.setInt(2, seatId);
            ResultSet rs = cp.executeQuery();
            
            if (rs.next()) {
                // Ghế đã tồn tại bản ghi Khóa trong DB
                java.sql.Timestamp lockedUntil = rs.getTimestamp("locked_until");
                int lockedBy = rs.getInt("locked_by_user");
                long currentTime = System.currentTimeMillis();
                
                // Nếu khóa đã hết hạn HOẶC do chính user này đang giữ -> Cho phép cập nhật gia hạn tiếp 5 phút
                if (lockedUntil == null || lockedUntil.getTime() < currentTime || lockedBy == userId) {
                    PreparedStatement up = conn.prepareStatement(updateSql);
                    up.setInt(1, userId);
                    up.setInt(2, showtimeId);
                    up.setInt(3, seatId);
                    return up.executeUpdate() > 0;
                } else {
                    // Ghế đang bị người khác giữ hợp lệ và chưa hết hạn
                    return false;
                }
            } else {
                // Ghế hoàn toàn trống (Chưa từng có ai bấm vào) -> Tiến hành chèn mới bản ghi giữ chỗ
                PreparedStatement ip = conn.prepareStatement(insertSql);
                ip.setInt(1, showtimeId);
                ip.setInt(2, seatId);
                ip.setInt(3, userId);
                return ip.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 3. Hàm hủy giữ ghế khi người dùng bỏ chọn (Uncheck) trên giao diện
    public void releaseSeat(int showtimeId, int seatId, int userId) {
        String sql = "DELETE FROM Showtime_Seat_Lock WHERE showtime_id = ? AND seat_id = ? AND locked_by_user = ?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, showtimeId);
            st.setInt(2, seatId);
            st.setInt(3, userId);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
   public int getSeatIdByLocation(int showtimeId, String seatRow, int seatNo) {
        int seatId = 0;
        
        // CÚ PHÁP CHUẨN: Tìm trực tiếp seat_id dựa vào việc liên kết bảng Seat và Showtime thông qua room_id
        String sql = "SELECT s.seat_id \n" +
                     "FROM Seat s \n" +
                     "JOIN Showtime st ON s.room_id = st.room_id \n" +
                     "WHERE st.showtime_id = ? \n" +
                     "  AND UPPER(TRIM(s.seat_row)) = UPPER(TRIM(?)) \n" +
                     "  AND s.seat_number = ?";

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, showtimeId);
            st.setString(2, seatRow.trim());
            st.setInt(3, seatNo);
            
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    seatId = rs.getInt("seat_id");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return seatId;
    }
}