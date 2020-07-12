package com.tdt.wheel.jwt.entity;


/**
 * @author tudoutiao
 * @version v1.0.0
 * @description: SysRoleEntity
 * @since 2020/07/11 17:31
 */
public class SysRoleEntity {
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 角色名称
     */
    private String roleName;

    public Long getRoleId() {
        return roleId;
    }

    public SysRoleEntity setRoleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }

    public String getRoleName() {
        return roleName;
    }

    public SysRoleEntity setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }
}
