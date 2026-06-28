const seats = document.querySelectorAll(".seat-item");

seats.forEach(seat => {
    seat.addEventListener("click", () => {
        
        if (seat.classList.contains("occupied")) {
            return;
        }
        seat.classList.toggle("selected");
        
        console.log(seat.dataset.seat);
        
    });
});