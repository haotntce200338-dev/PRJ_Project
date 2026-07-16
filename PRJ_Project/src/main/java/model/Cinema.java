/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Cinema {

    private int cinemaId;
    private String cinemaName;
    private String address;
    private String regionCode; // Ví dụ: "HCM", "CT"

    public Cinema() {
    }

    public Cinema(int cinemaId, String cinemaName, String address, String regionCode) {
        this.cinemaId = cinemaId;
        this.cinemaName = cinemaName;
        this.address = address;
        this.regionCode = regionCode;
    }

    // Getter và Setter
    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }
}
