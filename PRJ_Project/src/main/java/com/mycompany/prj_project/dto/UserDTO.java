package com.mycompany.prj_project.dto;

public class UserDTO {
    private String userID;
    private String password;
    private String fullName;
    private String email;
    private int roleID;
    private boolean status;

    // 1. Constructor rỗng
    public UserDTO() {
    }

    // 2. Constructor đầy đủ
    public UserDTO(String userID, String password, String fullName, String email, int roleID, boolean status) {
        this.userID = userID;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.roleID = roleID;
        this.status = status;
    }

    // 3. Getters và Setters
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}