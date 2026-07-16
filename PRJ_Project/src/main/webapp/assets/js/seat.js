document.addEventListener("DOMContentLoaded", () => {
    // 1. Lấy toàn bộ các ô ghế có class "seat-item"
    const seats = document.querySelectorAll(".seat-item");

    // 2. Lấy giá vé động được truyền từ Backend (Nếu không thấy thì mặc định là 60000)
    // Giá vé này đọc từ thẻ input ẩn <input type="hidden" id="movieTicketPrice" value="${currentMovie.price}"> có sẵn trong JSP của ông
    const priceInput = document.getElementById("movieTicketPrice");
    const TICKET_PRICE = priceInput ? parseFloat(priceInput.value) : 60000;

    // Lấy các thẻ hiển thị Text trên giao diện
    const displaySeats = document.getElementById("displaySelectedSeats");
    const displayTotal = document.getElementById("displayTotalAmount");

    // 3. Duyệt qua từng ghế để lắng nghe sự kiện Click
    seats.forEach(seat => {
        seat.addEventListener("click", () => {

            // Nếu ghế đã bị mua (occupied), không cho click chọn
            if (seat.classList.contains("occupied")) { 
                return; 
            }
            
            // Bật/tắt màu sắc trạng thái đang chọn
            seat.classList.toggle("selected"); 

            // Mảng chứa danh sách tên ghế được chọn (ví dụ: ["A1", "A2"])
            let selectedSeats = [];

            // Tìm nhanh tất cả các ghế đang có class "selected"
            const activeSeats = document.querySelectorAll(".seat-item.selected");
            activeSeats.forEach(s => {
                // Lấy giá trị từ thuộc tính data-seat="A${i}" trong file JSP của ông
                selectedSeats.push(s.dataset.seat);
            });

            // 4. Tính toán tổng số tiền dựa trên số lượng ghế thực tế nhân với giá vé gốc
            let totalAmount = selectedSeats.length * TICKET_PRICE;

            // 5. Cập nhật giao diện Modal trực quan cho người dùng xem
            if (displaySeats && displayTotal) {
                if (selectedSeats.length > 0) {
                    displaySeats.innerText = selectedSeats.join(', ');
                    displayTotal.innerText = totalAmount.toLocaleString('vi-VN') + " VND";
                } else {
                    displaySeats.innerText = "Chưa chọn";
                    displayTotal.innerText = "0 VND";
                }
            }

            // [NÂNG CẤP CHO TASK 2]: Đẩy dữ liệu vào form ẩn nếu có để chuẩn bị đem đi lưu Session/Thanh toán
            // Nếu nhóm ông có làm nút bấm "Confirm" để nộp dữ liệu, có thể gán thêm input ẩn tại đây.
            console.log("Danh sách ghế hiện tại:", selectedSeats);
        });
    });
});