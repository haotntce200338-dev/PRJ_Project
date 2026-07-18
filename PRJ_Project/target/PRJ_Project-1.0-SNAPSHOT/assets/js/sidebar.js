document.addEventListener("DOMContentLoaded", function () {

    const btn = document.querySelector(".dropdown-btn");
    const menu = document.querySelector(".dropdown-content");
    const arrow = document.querySelector(".arrow");

    btn.addEventListener("click", () => {
        menu.classList.toggle("show");

        arrow.classList.toggle("bi-chevron-down");
        arrow.classList.toggle("bi-chevron-up");
    });
});