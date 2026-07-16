<%-- 
    Document   : booking_showtime
    Created on : Jun 28, 2026, 10:38:35 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/include/header.jsp" /> 
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/booking.css">
    <script src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.js"></script>
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
            color: #dc3545;
            font-style: italic;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>CHỌN SUẤT CHIẾU</h2>
    
    <c:if test="${not empty ERROR_MSG}">
        <div style="color: red; font-weight: bold; margin-bottom: 15px;">${ERROR_MSG}</div>
    </c:if>

    <form id="bookingForm" action="booking" method="GET">
        <input type="hidden" name="action" value="select_showtime">
        <input type="hidden" name="movieId" value="${movieId}">
        
        <div class="form-group">
            <label><b>1. Chọn Khu Vực:</b></label><br>
            <select id="region" name="regionCode" onchange="this.form.submit()" class="form-select">
                <option value="">-- Chọn Tỉnh/Thành --</option>
                <option value="CT" ${selectedRegion == 'CT' ? 'selected' : ''}>Cần Thơ</option>
                <option value="HCM" ${selectedRegion == 'HCM' ? 'selected' : ''}>Hồ Chí Minh</option>
                <option value="HN" ${selectedRegion == 'HN' ? 'selected' : ''}>Hà Nội</option>
                <option value="DN" ${selectedRegion == 'DN' ? 'selected' : ''}>Đà Nẵng</option>
            </select>
        </div>

        <div class="form-group">
    <label><b>2. Chọn Rạp:</b></label><br>
    <select id="cinema" name="cinemaId" onchange="this.form.submit()" class="form-select">
        <option value="">-- Chọn Rạp --</option>
        <%
            // Đọc danh sách rạp được BookingServlet gửi sang
            java.util.List<model.Cinema> cinemas = (java.util.List<model.Cinema>) request.getAttribute("cinemaList");
            String selectedCinemaIdStr = request.getParameter("cinemaId");
            int selectedCinemaId = (selectedCinemaIdStr != null && !selectedCinemaIdStr.isEmpty()) ? Integer.parseInt(selectedCinemaIdStr) : -1;

            if (cinemas != null) {
                for (model.Cinema cinema : cinemas) {
        %>
                    <option value="<%= cinema.getCinemaId() %>" <%= (selectedCinemaId == cinema.getCinemaId()) ? "selected" : "" %>>
                        <%= cinema.getCinemaName() %>
                    </option>
        <%
                }
            }
        %>
    </select>
</div>

        <div class="form-group">
            <label><b>3. Chọn Ngày Xem:</b></label><br>
            <input type="date" id="showDate" name="showDate" class="form-input" 
                   value="${selectedShowDate}" onchange="this.form.submit()">
        </div>
        
        <c:if test="${not empty selectedCinemaId && not empty selectedShowDate}">
            <div id="showtime-section" style="margin-top: 20px;">
                <h4>4. Chọn Suất Chiếu Khả Dụng:</h4>
                <div id="showtime-list">
    <%
        // Lấy danh sách suất chiếu từ request
        java.util.List<model.Showtime> showtimes = (java.util.List<model.Showtime>) request.getAttribute("showtimeList");
        
        if (showtimes != null && !showtimes.isEmpty()) {
            for (model.Showtime st : showtimes) {
                String timeStr = "";
                if (st.getStartTime() != null) {
                    // Ép kiểu sang chuỗi hh:mm một cách an toàn nhất
                    String fullTime = st.getStartTime().toString(); 
                    if (fullTime.length() >= 5) {
                        timeStr = fullTime.substring(0, 5); // Lấy "HH:mm"
                    } else {
                        timeStr = fullTime;
                    }
                }
    %>
                <button type="button" class="btn-showtime" 
                        onclick="processBooking('<%= st.getShowtimeId() %>', '<%= timeStr %>')">
                    <%= timeStr %>
                </button>
    <%
            }
        } else {
    %>
            <p class="no-showtime">Không có suất chiếu nào cho ngày đã chọn!</p>
    <%
        }
    %>
</div>
            </div>
        </c:if>
    </form>
</div>

<script>
    const isRestrictedMovie = true; 
    const minAgeRequired = 18;

    // Thiết lập ngày tối thiểu là hôm nay
    document.getElementById('showDate').min = new Date().toISOString().split("T")[0];

    // Xử lý chuyển hướng khi chọn Suất chiếu
    function processBooking(showtimeId, timeLabel) {
        if (isRestrictedMovie) {
            let confirmAge = confirm("Bạn đang chọn suất chiếu lúc " + timeLabel + ".\nPhim này thuộc phân loại C" + minAgeRequired + ".\nBạn có chắc chắn mình đủ tuổi?");
            if (!confirmAge) {
                alert("Đã hủy chọn. Bạn hãy chọn lại suất chiếu khác phù hợp.");
                return; 
            }
        }
        // Chuyển hướng sang Servlet xử lý xác nhận đặt vé
        window.location.href = "booking?action=confirm_showtime&showtimeId=" + showtimeId;
    }
</script>
</body>
</html>
<jsp:include page="/WEB-INF/include/footer.jsp" />