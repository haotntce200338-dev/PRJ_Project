/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
import java.util.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

public class User {

    private int userId;
    private String fullName;
    private Date birthDate;
    private String role;

    public User() {
    }

    public User(int userId, String fullName, Date birthDate, String role) {
        this.userId = userId;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.role = role;
    }

    // Tính tuổi tự động từ ngày sinh để phục vụ logic check tuổi phim
    public int getAge() {
        if (this.birthDate == null) {
            return 0;
        }
        LocalDate birth = this.birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate now = LocalDate.now();
        return Period.between(birth, now).getYears();
    }

    // Getter và Setter
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
