const seats = document.querySelectorAll(".seat-item");

// Định nghĩa giá vé 
let totalAmount = selectedSeats.length * (window.CURRENT_MOVIE_PRICE || 60000);

seats.forEach(seat => {
    seat.addEventListener("click", () => {

        if (seat.classList.contains("occupied")) { //
            return; //
        }
        seat.classList.toggle("selected"); //

        console.log(seat.dataset.seat); //

        //  TỰ ĐỘNG TÍNH TIỀN
        let selectedSeats = [];

        // Tìm nhanh những ghế đang được chọn
        const activeSeats = document.querySelectorAll(".seat-item.selected");
        activeSeats.forEach(s => {
            selectedSeats.push(s.dataset.seat);
        });

        // Tính tổng số tiền
        let totalAmount = selectedSeats.length * TICKET_PRICE;

        // Lấy 2 thẻ hiển thị trên Modal để đổ chữ vào
        const displaySeats = document.getElementById("displaySelectedSeats");
        const displayTotal = document.getElementById("displayTotalAmount");

        // Nếu tìm thấy 2 thẻ đó thì cập nhật giao diện
        if (displaySeats && displayTotal) {
            if (selectedSeats.length > 0) {
                displaySeats.innerText = selectedSeats.join(', ');
                displayTotal.innerText = totalAmount.toLocaleString('vi-VN') + " VND";
            } else {
                displaySeats.innerText = "Chưa chọn";
                displayTotal.innerText = "0 VND";
            }
        }
    });
});