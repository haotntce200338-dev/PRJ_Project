<%-- 
    Document   : booking-modal
    Created on : Jun 27, 2026, 11:45:43 PM
    Author     : MY_PC
--%>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/booking.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="modal fade"
     id="bookingModal"
     tabindex="-1">

    <div class="modal-dialog modal-xl modal-dialog-centered">

        <div class="modal-content">

            <div class="modal-header">
                <h4 class="modal-title">Select Your Seats</h4>
                <button class="btn-close" data-bs-dismiss="modal"></button>
            </div>

            <div class="modal-body">
                <input type="hidden" id="movieTicketPrice" value="${moviePrice}">
                <div class="screen">SCREEN</div>

                <!-- SEAT LAYOUT -->
                <div class="seat-layout">
                    <c:set var="rows" value="${fn:split('A,B,C,D,E,F,G,H,I,J,K,L', ',')}" />

                    <c:forEach var="rowName" items="${rows}">
                        <c:set var="maxSeat" value="12" />
                        <c:if test="${rowName == 'C' || rowName == 'K'}"><c:set var="maxSeat" value="14" /></c:if>
                        <c:if test="${rowName == 'D' || rowName == 'J'}"><c:set var="maxSeat" value="16" /></c:if>
                        <c:if test="${rowName == 'E' || rowName == 'F' || rowName == 'G' || rowName == 'H' || rowName == 'I'}">
                            <c:set var="maxSeat" value="18" />
                        </c:if>

                        <c:set var="aisleIndex" value="${maxSeat / 2}" />

                        <div class="seat-row" data-row-name="${rowName}" data-max-seat="${maxSeat}">
                            <div class="row-label">${rowName}</div>

                            <c:forEach begin="1" end="${maxSeat}" var="i">
                                <c:set var="currentStatus" value="AVAILABLE" />
                                <c:set var="dbSeatId" value="0" />

                                <c:forEach var="dbSeat" items="${seatList}">
                                    <c:if test="${fn:trim(dbSeat.seatRow) == fn:trim(rowName) && dbSeat.seatNumber == i}">
                                        <c:set var="currentStatus" value="${dbSeat.status}" />
                                        <c:set var="dbSeatId" value="${dbSeat.seatId}" />
                                    </c:if>
                                </c:forEach>

                                <%-- Hàng D đến L tự động là vip, còn lại là normal --%>
                                <c:set var="isVip" value="${rowName == 'D' || rowName == 'E' || rowName == 'F' || rowName == 'G' || rowName == 'H' || rowName == 'I' || rowName == 'J' || rowName == 'K' || rowName == 'L'}" />
                                <c:set var="seatTypeClass" value="${isVip ? 'vip' : 'normal'}" />
                                <c:set var="statusClass" value="${fn:toLowerCase(fn:trim(currentStatus))}" />
                                
                                <%-- THAY ĐỔI: Tự động chia Block dựa vào lối đi ở giữa (aisleIndex) --%>
                                <c:set var="seatBlock" value="${i <= aisleIndex ? 'left' : 'right'}" />
                                
                                <div class="seat seat-item ${seatTypeClass} ${statusClass} ${i == aisleIndex ? 'aisle-right' : ''}" 
                                     data-seat="${rowName}${i}" 
                                     data-row="${rowName}"
                                     data-no="${i}"
                                     data-seat-id="${dbSeatId}"
                                     data-type="${seatTypeClass}"
                                     data-block="${seatBlock}"> <!-- ĐÃ THÊM data-block ĐỂ NHẬN BIẾT BLOCK TRÁI/PHẢI -->
                                    ${rowName}${i}
                                </div>
                            </c:forEach>
                        </div>
                    </c:forEach>
                </div>
            </div>

<div class="booking-info-summary mt-4 p-3" style="font-family: Arial, sans-serif; max-width: 1050px; margin: 20px auto 0 auto;">
    <div style="display: flex; flex-wrap: wrap; align-items: center; justify-content: space-between;">

        <!-- CỘT BÊN TRÁI: TĂNG GIẢM VÉ (GIỮ VẠCH KẺ PHẢI ĐỂ PHÂN TÁCH) -->
        <div style="flex: 1; min-width: 320px; padding-right: 40px; border-right: 1px solid #eee;">
            <h6 style="font-weight: bold; font-size: 15px; margin-bottom: 12px; color: #333; text-align: left; margin-top: 0;">
                Phân bổ loại vé (Tổng số ghế: <span id="totalSelectedCount" style="color: #dc3545; font-weight: bold;">0</span>)
            </h6>

            <!-- Dòng Người Lớn -->
            <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 10px; max-width: 300px;">
                <span style="font-size: 14px; color: #333;">Người lớn (Giá gốc):</span>
                <div style="display: flex; align-items: center; border: 1px solid #ced4da; border-radius: 4px; overflow: hidden; height: 28px;">
                    <button class="btn-minus-ticket" type="button" data-type="adult" style="width: 30px; height: 100%; border: none; background: #e9ecef; cursor: pointer; font-weight: bold; font-size: 14px; padding: 0;">-</button>
                    <input type="text" id="quantityAdult" value="0" readonly style="width: 45px; height: 100%; text-align: center; border: none; border-left: 1px solid #ced4da; border-right: 1px solid #ced4da; background: #fff; font-size: 14px; outline: none; padding: 0;">
                    <button class="btn-plus-ticket" type="button" data-type="adult" style="width: 30px; height: 100%; border: none; background: #e9ecef; cursor: pointer; font-weight: bold; font-size: 14px; padding: 0;">+</button>
                </div>
            </div>

            <!-- Dòng Học Sinh - SV -->
            <div style="display: flex; justify-content: space-between; align-items: center; max-width: 300px;">
                <span style="font-size: 14px; color: #333;">Học sinh - SV (Giảm 20%):</span>
                <div style="display: flex; align-items: center; border: 1px solid #ced4da; border-radius: 4px; overflow: hidden; height: 28px;">
                    <button class="btn-minus-ticket" type="button" data-type="student" style="width: 30px; height: 100%; border: none; background: #e9ecef; cursor: pointer; font-weight: bold; font-size: 14px; padding: 0;">-</button>
                    <input type="text" id="quantityStudent" value="0" readonly style="width: 45px; height: 100%; text-align: center; border: none; border-left: 1px solid #ced4da; border-right: 1px solid #ced4da; background: #fff; font-size: 14px; outline: none; padding: 0;">
                    <button class="btn-plus-ticket" type="button" data-type="student" style="width: 30px; height: 100%; border: none; background: #e9ecef; cursor: pointer; font-weight: bold; font-size: 14px; padding: 0;">+</button>
                </div>
            </div>
        </div>

        <!-- CỘT BÊN PHẢI: THÔNG TIN GHẾ VÀ TỔNG TIỀN -->
        <div style="flex: 1; min-width: 250px; text-align: right; padding-left: 40px;">
            <p style="font-size: 15px; margin-bottom: 6px; color: #333;">
                Ghế đã chọn: <strong id="displaySelectedSeats" style="color: #28a745;">Chưa chọn</strong>
            </p>
            <p style="font-size: 18px; font-weight: bold; margin-bottom: 0; color: #333;">
                Tổng tiền: <span id="displayTotalAmount" style="color: #dc3545;">0 VND</span>
            </p>
        </div>

    </div>
</div>

            <div class="seat-legend">
                <!-- Phân loại Ghế (Loại ghế) -->
                <div class="legend-item">
                    <div class="seat normal"></div>
                    Ghế Tiêu Chuẩn
                </div>
                <div class="legend-item">
                    <div class="seat vip"></div>
                    Ghế VIP
                </div>

                <!-- Phân loại Trạng thái ghế -->
                <div class="legend-item">
                    <div class="seat selected"></div>
                    Đang chọn
                </div>
                <div class="legend-item">
                    <div class="seat hold"></div>
                    Tạm giữ chỗ
                </div>
                <div class="legend-item">
                    <div class="seat occupied"></div>
                    Đã đặt
                </div>

                <div class="modal-footer">
                    <button type="button" id="btnCancelBooking" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                    <button type="button" id="btnConfirmBooking" class="btn btn-warning">Confirm</button>
                </div>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function () {
            let selectedSeats = [];
            let basePrice = parseInt($("#movieTicketPrice").val()) || 0;
            let vipSurcharge = 20000;

            let adultRate = 1.0;
            let studentRate = 0.8;

            function getShowtimeId() {
                let urlParams = new URLSearchParams(window.location.search);
                return urlParams.get('showtimeId') || "1"; 
            }

            function updateBookingSummary() {
                let totalSeats = selectedSeats.length;
                $("#totalSelectedCount").text(totalSeats);

                if (totalSeats === 0) {
                    $("#displaySelectedSeats").text("Chưa chọn");
                    $("#displayTotalAmount").text("0 VND");
                    $("#quantityAdult").val(0);
                    $("#quantityStudent").val(0);
                    return;
                }

                let seatLabels = selectedSeats.map(s => s.label).sort();
                $("#displaySelectedSeats").text(seatLabels.join(", "));

                let adultCount = parseInt($("#quantityAdult").val()) || 0;
                let studentCount = parseInt($("#quantityStudent").val()) || 0;

                if (adultCount + studentCount !== totalSeats) {
                    if (totalSeats > (adultCount + studentCount)) {
                        let extra = totalSeats - (adultCount + studentCount);
                        adultCount += extra;
                    } else {
                        let reduceAmount = (adultCount + studentCount) - totalSeats;
                        if (adultCount >= reduceAmount) {
                            adultCount -= reduceAmount;
                        } else {
                            reduceAmount -= adultCount;
                            adultCount = 0;
                            studentCount -= reduceAmount;
                        }
                    }
                    $("#quantityAdult").val(adultCount);
                    $("#quantityStudent").val(studentCount);
                }

                let totalAmount = 0;
                let seatPrices = selectedSeats.map(s => {
                    return s.type === "vip" ? (basePrice + vipSurcharge) : basePrice;
                });

                seatPrices.sort((a, b) => a - b);

                for (let i = 0; i < studentCount; i++) {
                    totalAmount += seatPrices[i] * studentRate;
                }
                for (let i = studentCount; i < seatPrices.length; i++) {
                    totalAmount += seatPrices[i] * adultRate;
                }

                $("#displayTotalAmount").text(totalAmount.toLocaleString('vi-VN') + " VND");
            }

            // Click Chọn / Bỏ chọn ghế
            $(".seat-layout").on("click", ".seat-item", function () {
                let $seat = $(this);

                if ($seat.hasClass("occupied") || $seat.hasClass("hold"))
                    return;

                let seatLabel = $seat.data("seat");
                let seatType = $seat.data("type");
                let seatBlock = $seat.data("block"); // ĐÃ LẤY THÊM dữ liệu block (left/right) từ HTML
                let seatId = $seat.data("seat-id");
                let seatRow = $seat.data("row"); 
                let seatNo = parseInt($seat.data("no"));   
                let showtimeId = getShowtimeId();

                let isSelected = $seat.hasClass("selected");

                // --- ĐÃ BỔ SUNG ĐIỀU KIỆN 1: GIỚI HẠN TỐI ĐA 6 GHẾ ---
                if (!isSelected && selectedSeats.length >= 6) {
                    alert("Bạn chỉ được phép chọn tối đa 6 ghế cho mỗi giao dịch để tránh đầu cơ vé!");
                    return;
                }

                // --- ĐÃ THAY ĐỔI ĐIỀU KIỆN 2: CHẶN CHỌN KHÁC LOẠI GHẾ VÀ KHÁC BLOCK (LEFT/RIGHT) ---
                if (!isSelected && selectedSeats.length > 0) {
                    // 2a. Kiểm tra loại ghế (VIP/Normal)
                    let firstSeatType = selectedSeats[0].type;
                    if (seatType !== firstSeatType) {
                        let textType = firstSeatType === "vip" ? "Ghế VIP" : "Ghế Tiêu Chuẩn";
                        alert("Để áp dụng chính xác giá vé HSSV, bạn chỉ được chọn đồng nhất các ghế cùng loại trong 1 giao dịch. Hiện tại bạn đang chọn: " + textType);
                        return;
                    }

                    // 2b. KIỂM TRA ĐỒNG NHẤT BLOCK GHẾ (CHẶN BẮT CẢ HAI BLOCK LEFT VÀ RIGHT)
                    let firstSeatBlock = selectedSeats[0].block;
                    if (seatBlock !== firstSeatBlock) {
                        let textBlock = firstSeatBlock === "left" ? "Block ghế bên TRÁI" : "Block ghế bên PHẢI";
                        alert("Quy định rạp phim: Bạn chỉ được chọn các ghế nằm trong cùng một khu vực xếp ghế cho một giao dịch. Hiện tại bạn đang chọn các ghế ở: " + textBlock);
                        return;
                    }
                }

                let actionType = isSelected ? "release" : "hold";

                $.ajax({
                    url: "${pageContext.request.contextPath}/select-seat",
                    type: "POST",
                    data: {
                        action: actionType,
                        showtimeId: showtimeId,
                        seatId: seatId,
                        seatRow: seatRow, 
                        seatNo: seatNo    
                    },
                    success: function (response) {
                        if (response === "HOLD_SUCCESS") {
                            $seat.addClass("selected");
                            // ĐÃ SỬA: Lưu thêm thuộc tính block vào mảng selectedSeats
                            selectedSeats.push({label: seatLabel, type: seatType, block: seatBlock, row: seatRow, no: seatNo});
                            updateBookingSummary();
                        } else if (response === "RELEASE_SUCCESS") {
                            $seat.removeClass("selected");
                            selectedSeats = selectedSeats.filter(s => s.label !== seatLabel);
                            updateBookingSummary();
                        } else if (response === "HOLD_FAILED") {
                            alert("Ghế " + seatLabel + " vừa có người khác giữ chỗ nhanh hơn. Vui lòng chọn ghế khác!");
                            $seat.removeClass("selected").addClass("hold"); 
                        } else if (response === "NOT_LOGGED_IN") {
                            alert("Vui lòng đăng nhập hệ thống để thực hiện chọn ghế!");
                        } else {
                            alert("Đã xảy ra lỗi hệ thống trong quá trình giữ chỗ!");
                        }
                    },
                    error: function () {
                        alert("Không thể kết nối đến máy chủ hệ thống!");
                    }
                });
            });
          
            // Tăng số lượng vé (+)
            $(".btn-plus-ticket").on("click", function () {
                let totalSeats = selectedSeats.length;
                if (totalSeats === 0) return;

                let type = $(this).data("type");
                let adultCount = parseInt($("#quantityAdult").val());
                let studentCount = parseInt($("#quantityStudent").val());

                if (type === 'adult' && studentCount > 0) {
                    $("#quantityAdult").val(adultCount + 1);
                    $("#quantityStudent").val(studentCount - 1);
                } else if (type === 'student' && adultCount > 0) {
                    $("#quantityStudent").val(studentCount + 1);
                    $("#quantityAdult").val(adultCount - 1);
                }
                updateBookingSummary();
            });

            // Giảm số lượng vé (-)
            $(".btn-minus-ticket").on("click", function () {
                let totalSeats = selectedSeats.length;
                if (totalSeats === 0) return;

                let type = $(this).data("type");
                let adultCount = parseInt($("#quantityAdult").val());
                let studentCount = parseInt($("#quantityStudent").val());

                if (type === 'adult' && adultCount > 0) {
                    $("#quantityAdult").val(adultCount - 1);
                    $("#quantityStudent").val(studentCount + 1);
                } else if (type === 'student' && studentCount > 0) {
                    $("#quantityStudent").val(studentCount - 1);
                    $("#quantityAdult").val(adultCount + 1);
                }
                updateBookingSummary();
            });

            // Bấm nút CANCEL giải phóng toàn bộ ghế
            $("#btnCancelBooking").on("click", function () {
                releaseAllSelectedSeats();
            });

            // Khi tắt ngang Modal bằng nút X hoặc click out
            $("#bookingModal").on("hidden.bs.modal", function () {
                releaseAllSelectedSeats();
            });

            function releaseAllSelectedSeats() {
                if (selectedSeats.length === 0) return;

                let showtimeId = getShowtimeId();
                let seatIds = [];

                $(".seat-layout .seat-item.selected").each(function () {
                    seatIds.push($(this).data("seat-id"));
                });

                if(seatIds.length > 0) {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/select-seat",
                        type: "POST",
                        data: {
                            action: "release-multiple", 
                            showtimeId: showtimeId,
                            seatIds: seatIds.join(",") 
                        }
                    });
                }

                $(".seat-layout .seat-item.selected").removeClass("selected");
                selectedSeats = [];
                updateBookingSummary();
            }

            // Bấm CONFIRM chuyển tiếp thanh toán
            $("#btnConfirmBooking").on("click", function () {
                if (selectedSeats.length === 0) {
                    alert("Vui lòng chọn ít nhất một ghế trước khi tiếp tục thanh toán!");
                    return;
                }

                let seatLabels = selectedSeats.map(s => s.label);
                let seatIds = [];

                $(".seat-layout .seat-item.selected").each(function () {
                    seatIds.push($(this).data("seat-id"));
                });

                let adultCount = $("#quantityAdult").val();
                let studentCount = $("#quantityStudent").val();
                let totalAmount = $("#displayTotalAmount").text().replace(/[^0-9]/g, ""); 
let showtimeId=getShowtimeId();
                let paymentUrl =
                       "payment?showtimeId=" + encodeURIComponent(showtimeId)
    + "&seats=" + encodeURIComponent(seatLabels.join(","))
    + "&seatIds=" + encodeURIComponent(seatIds.join(","))
    + "&adult=" + adultCount
    + "&student=" + studentCount
    + "&amount=" + totalAmount;

                window.location.href = paymentUrl;
            });
        });
    </script>
</div>