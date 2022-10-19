package com.qfedu.vo;

import java.util.Date;

public class AdminInfoVo {
    private Integer adminId;
    private String name;
    private String adminCode;
    private String telephone;
    private String email;
    private String enrollDate;
    private String roleName;

    public AdminInfoVo() {
    }

    public AdminInfoVo(Integer adminId, String name, String adminCode, String telephone, String email, String enrollDate, String roleName) {
        this.adminId = adminId;
        this.name = name;
        this.adminCode = adminCode;
        this.telephone = telephone;
        this.email = email;
        this.enrollDate = enrollDate;
        this.roleName = roleName;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdminCode() {
        return adminCode;
    }

    public void setAdminCode(String adminCode) {
        this.adminCode = adminCode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(String enrollDate) {
        this.enrollDate = enrollDate;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
