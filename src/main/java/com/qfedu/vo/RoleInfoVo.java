package com.qfedu.vo;

public class RoleInfoVo {
    private Integer roleId;
    private String roleName;
    private String moduleNames;

    public RoleInfoVo() {
    }

    public RoleInfoVo(Integer roleId, String roleName, String moduleNames) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.moduleNames = moduleNames;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getModuleNames() {
        return moduleNames;
    }

    public void setModuleNames(String moduleNames) {
        this.moduleNames = moduleNames;
    }
}
