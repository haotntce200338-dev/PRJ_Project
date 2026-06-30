document.querySelectorAll(".seat-item").forEach(seat => {

    if (seat.classList.contains("occupied"))
        return;

    seat.addEventListener("click", () => {

        seat.classList.toggle("selected");

    });

});