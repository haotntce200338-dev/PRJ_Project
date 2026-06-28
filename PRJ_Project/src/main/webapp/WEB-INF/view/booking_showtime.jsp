<%-- 
    Document   : booking_showtime
    Created on : Jun 28, 2026, 10:38:35 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Chọn Suất Chiếu</title>
    <style>
        .container {
            max-width: 600px; 
            margin: 50px auto; 
            font-family: Arial, sans-serif;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-select, .form-input {
            width: 100%; 
            padding: 8px; 
            margin-top: 5px;
            box-sizing: border-box;
        }
        .btn-showtime {
            background-color: #f8f9fa;
            border: 1px solid #ced4da;
            padding: 10px 20px;
            margin: 5px;
            cursor: pointer;
            border-radius: 5px;
            font-weight: bold;
            transition: all 0.2s;
        }
        .btn-showtime:hover {
            background-color: #007bff;
            color: white;
            border-color: #007bff;
        }
        .no-showtime {
            color: #6c757d;
            font-style: italic;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>BƯỚC 1: CHỌN SUẤT CHIẾU</h2>
    
    <% if(request.getAttribute("ERROR_MSG") != null) { %>
        <div style="color: red; font-weight: bold; margin-bottom: 15px;"><%= request.getAttribute("ERROR_MSG") %></div>
    <% } %>

    <form action="booking" method="GET">
        <input type="hidden" name="action" value="select_showtime">
        <input type="hidden" id="movieId" name="movieId" value="${not empty movieId ? movieId : '1'}">
        
        <div class="form-group">
            <label><b>1. Chọn Khu Vực:</b></label><br>
            <select id="region" onchange="loadCinemas()" class="form-select">
                <option value="">-- Chọn Tỉnh/Thành --</option>
                <option value="CT">Cần Thơ</option>
                <option value="HCM">Hồ Chí Minh</option>
                <option value="HN">Hà Nội</option>
                <option value="DN">Đà Nẵng</option>
            </select>
        </div>

        <div class="form-group">
            <label><b>2. Chọn Rạp:</b></label><br>
            <select id="cinema" name="cinemaId" class="form-select">
                <option value="">-- Chọn Rạp --</option>
            </select>
        </div>

        <div class="form-group">
            <label><b>3. Chọn Ngày Xem:</b></label><br>
            <input type="date" id="showDate" name="showDate" class="form-input" onchange="checkInputsAndLoadShowtimes()">
        </div>
        
        <div id="showtime-section" style="margin-top: 20px; display: none;">
            <h4>4. Chọn Suất Chiếu Khả Dụng:</h4>
            <div id="showtime-list"></div>
        </div>
    </form>
</div>

<script>
    const isRestrictedMovie = true; 
    const minAgeRequired = 18;

    // Lấy contextPath của ứng dụng gán vào biến Javascript toàn cục để an toàn tuyệt đối
    const contextPath = "${pageContext.request.contextPath}";

    // Thiết lập ngày tối thiểu là hôm nay
    document.getElementById('showDate').min = new Date().toISOString().split("T")[0];

    // Ajax 1: Chọn Khu vực -> Load Rạp
    function loadCinemas() {
        const regionCode = document.getElementById("region").value;
        const cinemaSelect = document.getElementById("cinema");
        
        cinemaSelect.innerHTML = '<option value="">-- Chọn Rạp --</option>';
        document.getElementById("showtime-section").style.display = "none"; 
        
        if (regionCode === "") return;

        // Dùng phép cộng chuỗi an toàn
        fetch(contextPath + "/GetCinemaAjax?regionCode=" + regionCode)
            .then(response => response.json())
            .then(data => {
                data.forEach(cinema => {
                    let option = document.createElement("option");
                    option.value = cinema.id;
                    option.textContent = cinema.name;
                    cinemaSelect.appendChild(option);
                });
                cinemaSelect.onchange = checkInputsAndLoadShowtimes;
            })
            .catch(error => console.error("Lỗi tải rạp:", error));
    }

    // Ajax 2: Đủ điều kiện rạp và ngày -> Load toàn bộ Suất chiếu tương ứng
    function checkInputsAndLoadShowtimes() {
        const movieId = document.getElementById("movieId").value;
        const cinemaId = document.getElementById("cinema").value;
        const showDate = document.getElementById("showDate").value;
        const section = document.getElementById("showtime-section");
        const listContainer = document.getElementById("showtime-list");

        if (cinemaId !== "" && showDate !== "") {
            section.style.display = "block";
            listContainer.innerHTML = "<em>Đang tải suất chiếu...</em>";

            // ĐÃ SỬA: Thay thế dấu backtick bằng cộng chuỗi truyền thống để loại bỏ SyntaxError
            fetch(contextPath + "/GetShowtimeAjax?movieId=" + movieId + "&cinemaId=" + cinemaId + "&showDate=" + showDate)
                .then(response => response.json())
                .then(data => {
                    listContainer.innerHTML = ""; // Xóa dòng đang tải đi
                    
                    if(data.length === 0) {
                        listContainer.innerHTML = '<p class="no-showtime">Rạp không có suất chiếu nào vào ngày này. Vui lòng chọn ngày hoặc rạp khác!</p>';
                    } else {
                        // Duyệt dữ liệu từ DB và tạo nút bấm động
                        data.forEach(st => {
                            let btn = document.createElement("button");
                            btn.type = "button";
                            btn.className = "btn-showtime";
                            btn.textContent = st.startTime;
                            btn.onclick = function() { processBooking(st.showtimeId, st.startTime); };
                            listContainer.appendChild(btn);
                        });
                    }
                })
                .catch(error => {
                    console.error("Lỗi tải suất chiếu:", error);
                    listContainer.innerHTML = '<span style="color:red;">Lỗi hệ thống khi tải suất chiếu.</span>';
                });
        } else {
            section.style.display = "none";
        }
    }

    function processBooking(showtimeId, timeLabel) {
        if (isRestrictedMovie) {
            let confirmAge = confirm("Bạn đang chọn suất chiếu lúc " + timeLabel + ".\nPhim này thuộc phân loại C" + minAgeRequired + ".\nBạn có chắc chắn mình đủ tuổi?");
            if (!confirmAge) {
                alert("Đã hủy chọn. Bạn hãy chọn lại suất chiếu khác phù hợp.");
                return; 
            }
        }
        const movieId = document.getElementById("movieId").value;
        window.location.href = "booking?action=confirm_showtime&showtimeId=" + showtimeId + "&movieId=" + movieId;
    }
</script>
</body>
</html>