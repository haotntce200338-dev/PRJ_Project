<%-- 
    Document   : payment
    Created on : Jul 6, 2026, 2:08:01 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/include/header.jsp" /> 

<div class="container my-5" style="max-width: 600px; margin: 40px auto; font-family: Arial, sans-serif;">
    <h3 class="text-center mb-4" style="font-weight: bold; color: #333;">HÓA ĐƠN THANH TOÁN</h3>
    
    <div style="background: #fff; padding: 25px; border-radius: 8px; box-shadow: 0 4px 15px rgba(0,0,0,0.1); border: 1px solid #eee;">
        
        <!-- HIỂN THỊ THÔNG TIN VÉ -->
        <div style="margin-bottom: 20px; line-height: 2;">
            <p><strong>Danh sách ghế:</strong> <span style="color: #28a745; font-size: 16px;">${seats}</span></p>
            <p><strong>Vé người lớn:</strong> ${adult}</p>
            <p><strong>Vé học sinh - SV:</strong> ${student}</p>
            <hr style="border: 0; border-top: 1px dashed #ccc; margin: 15px 0;">
            <p style="font-size: 18px; font-weight: bold;">
                Tổng số tiền: <span style="color: #dc3545;">${amount} VND</span>
            </p>
        </div>

        <!-- FORM GỬI POST SANG ORDER SERVLET -->
        <form action="${pageContext.request.contextPath}/order" method="POST">
            
         <!-- 🛠️ RÚT GỌN LẠI: Vì Servlet đã truyền chính xác tên biến "showtimeId" sang rồi -->
            <input type="hidden" name="showtimeId" value="${showtimeId}"> 
            <!-- Các thông tin ngầm cũ giữ nguyên -->
            <input type="hidden" name="seatIdsString" value="${seatIds}">
            <input type="hidden" name="totalAmount" value="${amount}">

            <!-- CHỌN PHƯƠNG THỨC THANH TOÁN -->
            <div class="mb-4" style="margin-top: 20px;">
                <label style="display: block; font-weight: bold; margin-bottom: 8px;">Chọn phương thức thanh toán:</label>
                <div style="margin-bottom: 8px;">
                    <input type="radio" name="paymentMethod" value="COD" id="cod" checked>
                    <label for="cod">Thẻ ngân hàng nội địa</label>
                </div>
                <div>
                    <input type="radio" name="paymentMethod" value="VNPAY" id="vnpay">
                    <label for="vnpay">Ví điện tử</label>
                </div>
            </div>

            <!-- NÚT XÁC NHẬN CHÍNH THỨC MUA VÉ -->
            <div style="display: flex; gap: 15px; margin-top: 30px;">
                <button type="button" onclick="history.back()" class="btn btn-secondary" style="flex: 1; padding: 12px;">Quay lại</button>
                <button type="submit" class="btn btn-danger" style="flex: 2; padding: 12px; font-weight: bold; background-color: #dc3545; color: white; border: none; border-radius: 4px; cursor: pointer;">
                    Xác nhận thanh toán
                </button>
            </div>
        </form>
    </div>
</div>

<jsp:include page="/WEB-INF/include/footer.jsp" />