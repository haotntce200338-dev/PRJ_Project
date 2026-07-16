/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */

public class Seat {
    private int seatId;
    private int roomId;
    private String seatRow;
    private int seatNumber;
    private String seatType;
    private String status; //Thuộc tính động để lưu 'AVAILABLE', 'LOCKED', 'BOOKED' khi LEFT JOIN

    // Constructor không tham số (Mặc định bắt buộc phải có)
    public Seat() {
    }

    // Constructor đầy đủ tham số
    public Seat(int seatId, int roomId, String seatRow, int seatNumber, String seatType, String status) {
        this.seatId = seatId;
        this.roomId = roomId;
        this.seatRow = seatRow;
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.status = status;
    }

    // Getter và Setter cho các thuộc tính
    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(String seatRow) {
        this.seatRow = seatRow;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Hàm toString() tiện cho việc debug nếu cần
    @Override
    public String toString() {
        return "Seat{" + "seatId=" + seatId + ", roomId=" + roomId + ", seatRow=" + seatRow + ", seatNumber=" + seatNumber + ", seatType=" + seatType + ", status=" + status + '}';
    }
}