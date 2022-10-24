package com.qfedu.vo;

public class AdminRoleVo {
    private Integer adminId;
    private String name;
    private String adminCode;
    private String password;
    private String telephone;
    private String email;
    private String roleIds;

    public AdminRoleVo() {
    }

    public AdminRoleVo(Integer adminId, String name, String adminCode, String password, String telephone, String email, String roleIds) {
        this.adminId = adminId;
        this.name = name;
        this.adminCode = adminCode;
        this.password = password;
        this.telephone = telephone;
        this.email = email;
        this.roleIds = roleIds;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }
}
