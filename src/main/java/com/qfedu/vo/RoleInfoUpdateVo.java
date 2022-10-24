package com.qfedu.vo;

public class RoleInfoUpdateVo {
    private Integer roleId;
    private String roleName;
    private String moduleIds;

    public RoleInfoUpdateVo() {
    }

    public RoleInfoUpdateVo(Integer roleId, String roleName, String moduleIds) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.moduleIds = moduleIds;
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

    public String getModuleIds() {
        return moduleIds;
    }

    public void setModuleIds(String moduleIds) {
        this.moduleIds = moduleIds;
    }
}
