<%-- 
    Document   : booking-seat
    Created on : Jul 6, 2026, 2:45:31 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<jsp:include page="/WEB-INF/include/header.jsp" /> 

<div class="container my-5 text-center" style="max-width: 600px; margin: 50px auto; font-family: Arial, sans-serif;">
    <div style="background: #fff; padding: 40px; border-radius: 8px; box-shadow: 0 4px 15px rgba(0,0,0,0.1); border-top: 5px solid #28a745;">
        <h2 style="color: #28a745; font-weight: bold;">🎉 ĐẶT VÉ THÀNH CÔNG! 🎉</h2>
        <p style="font-size: 16px; margin-top: 20px; color: #555;">
            Hệ thống đã ghi nhận lịch đặt vé của bạn thành công!
        </p>
        
        <div style="background: #f8f9fa; padding: 15px; border-radius: 4px; margin: 25px 0; text-align: left;">
            <p><strong>Mã hóa đơn (Order ID):</strong> <span style="color: #dc3545; font-weight: bold;">#${ORDER_ID}</span></p>
            <p><strong>Phương thức thanh toán:</strong> ${PAYMENT_METHOD == 'COD' ? 'Thanh toán bằng thẻ ngân hàng' : 'Ví điện tử'}</p>
            <p><strong>Trạng thái:</strong> <span style="color: #28a745; font-weight: bold;">Đã hoàn tất</span></p>
        </div>

        <a href="${pageContext.request.contextPath}/" class="btn btn-primary" style="padding: 10px 20px; background-color: #007bff; color: white; text-decoration: none; border-radius: 4px; display: inline-block;">
            Quay về trang chủ
        </a>
    </div>
</div>

<jsp:include page="/WEB-INF/include/footer.jsp" />