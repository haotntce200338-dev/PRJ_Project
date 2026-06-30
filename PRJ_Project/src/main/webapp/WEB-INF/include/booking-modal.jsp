<%-- 
    Document   : booking-modal
    Created on : Jun 27, 2026, 11:45:43 PM
    Author     : MY_PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="modal fade"
     id="bookingModal"
     tabindex="-1">

    <div class="modal-dialog modal-xl modal-dialog-centered">

        <div class="modal-content">

            <div class="modal-header">

                <h4 class="modal-title">Select Your Seats</h4>

                <button class="btn-close"
                        data-bs-dismiss="modal">
                </button>

            </div>

            <div class="modal-body">

                <div class="screen">
                    SCREEN
                </div>
                <!-- SEAT LAYOUT -->

                <div class="seat-layout">
                    <!-- ROW A -->
                    <div class="seat-row">
                        <div class="row-label">A</div>

                        <c:forEach begin="1" end="6" var="i">
                            <div class="seat seat-item"
                                 data-seat="A${i}">${i}</div>
                        </c:forEach>



                        <c:forEach begin="7" end="12" var="i">
                            <div class="seat seat-item"
                                 data-seat="A${i}">${i}</div>
                        </c:forEach>
                    </div>

                    <!-- ROW B -->
                    <div class="seat-row">
                        <div class="row-label">B</div>

                        <c:forEach begin="1" end="6" var="i">
                            <div class="seat seat-item"
                                 data-seat="B${i}">${i}</div>
                        </c:forEach>



                        <c:forEach begin="7" end="12" var="i">
                            <div class="seat seat-item"
                                 data-seat="B${i}">${i}</div>
                        </c:forEach>
                    </div>

                    <!-- ROW C -->
                    <div class="seat-row">
                        <div class="row-label">C</div>

                        <c:forEach begin="1" end="7" var="i">
                            <div class="seat seat-item"
                                 data-seat="C${i}">${i}</div>
                        </c:forEach>

                        <div class="seat-aisle"></div>

                        <c:forEach begin="8" end="14" var="i">
                            <div class="seat seat-item"
                                 data-seat="C${i}">${i}</div>
                        </c:forEach>
                    </div>

                    <!-- ROW D -->
                    <div class="seat-row">
                        <div class="row-label">D</div>

                        <c:forEach begin="1" end="8" var="i">
                            <div class="seat seat-item"
                                 data-seat="D${i}">${i}</div>
                        </c:forEach>

                        <div class="seat-aisle"></div>

                        <c:forEach begin="9" end="16" var="i">
                            <div class="seat seat-item"
                                 data-seat="D${i}">${i}</div>
                        </c:forEach>
                    </div>

                    <!-- ROW E -->
                    <div class="seat-row">
                        <div class="row-label">E</div>

                        <c:forEach begin="1" end="9" var="i">
                            <div class="seat seat-item"
                                 data-seat="E${i}">${i}</div>
                        </c:forEach>

                        <div class="seat-aisle"></div>

                        <c:forEach begin="10" end="18" var="i">
                            <div class="seat seat-item"
                                 data-seat="E${i}">${i}</div>
                        </c:forEach>
                    </div>

                    <!-- ROW F -->
                    <div class="seat-row">
                        <div class="row-label">F</div>

                        <c:forEach begin="1" end="9" var="i">
                            <div class="seat seat-item"
                                 data-seat="F${i}">${i}</div>
                        </c:forEach>

                        <div class="seat-aisle"></div>

                        <c:forEach begin="10" end="18" var="i">
                            <div class="seat seat-item"
                                 data-seat="F${i}">${i}</div>
                        </c:forEach>
                    </div>

                    <!-- ROW G -->
                    <div class="seat-row">
                        <div class="row-label">G</div>

                        <c:forEach begin="1" end="9" var="i">
                            <div class="seat seat-item"
                                 data-seat="G${i}">${i}</div>
                        </c:forEach>

                        <div class="seat-aisle"></div>

                        <c:forEach begin="10" end="18" var="i">
                            <div class="seat seat-item"
                                 data-seat="G${i}">${i}</div>
                        </c:forEach>
                    </div>

                    <!-- ROW H -->
                    <div class="seat-row">
                        <div class="row-label">H</div>

                        <c:forEach begin="1" end="9" var="i">
                            <div class="seat seat-item"
                                 data-seat="H${i}">${i}</div>
                        </c:forEach>

                        <div class="seat-aisle"></div>

                        <c:forEach begin="10" end="18" var="i">
                            <div class="seat seat-item"
                                 data-seat="H${i}">${i}</div>
                        </c:forEach>
                    </div>

                    <!-- ROW I -->
                    <div class="seat-row">
                        <div class="row-label">I</div>

                        <c:forEach begin="1" end="9" var="i">
                            <div class="seat seat-item"
                                 data-seat="I${i}">${i}</div>
                        </c:forEach>

                        <div class="seat-aisle"></div>

                        <c:forEach begin="10" end="18" var="i">
                            <div class="seat seat-item"
                                 data-seat="I${i}">${i}</div>
                        </c:forEach>
                    </div>

                    <!-- ROW J -->
                    <div class="seat-row">
                        <div class="row-label">J</div>

                        <c:forEach begin="1" end="8" var="i">
                            <div class="seat seat-item"
                                 data-seat="J${i}">${i}</div>
                        </c:forEach>

                        <div class="seat-aisle"></div>

                        <c:forEach begin="9" end="16" var="i">
                            <div class="seat seat-item"
                                 data-seat="J${i}">${i}</div>
                        </c:forEach>
                    </div>

                    <!-- ROW K -->
                    <div class="seat-row">
                        <div class="row-label">K</div>

                        <c:forEach begin="1" end="7" var="i">
                            <div class="seat seat-item"
                                 data-seat="K${i}">${i}</div>
                        </c:forEach>

                        <div class="seat-aisle"></div>

                        <c:forEach begin="8" end="14" var="i">
                            <div class="seat seat-item"
                                 data-seat="K${i}">${i}</div>
                        </c:forEach>
                    </div>

                    <!-- ROW L -->
                    <div class="seat-row">
                        <div class="row-label">L</div>

                        <c:forEach begin="1" end="6" var="i">
                            <div class="seat seat-item"
                                 data-seat="L${i}">${i}</div>
                        </c:forEach>

                        <div class="seat-aisle"></div>

                        <c:forEach begin="7" end="12" var="i">
                            <div class="seat seat-item"
                                 data-seat="L${i}">${i}</div>
                        </c:forEach>
                    </div>
                </div>

                <div class="seat-legend">

                    <div class="legend-item">
                        <div class="seat available"></div>
                        Available
                    </div>

                    <div class="legend-item">
                        <div class="seat selected"></div>
                        Selected
                    </div>

                    <div class="legend-item">
                        <div class="seat occupied"></div>
                        Occupied
                    </div>

                </div>
            </div>

            <div class="modal-footer">

                <button class="btn btn-secondary"
                        data-bs-dismiss="modal">
                    Cancel
                </button>

                <button class="btn btn-warning">
                    Confirm
                </button>

            </div>

        </div>

    </div>

</div>
