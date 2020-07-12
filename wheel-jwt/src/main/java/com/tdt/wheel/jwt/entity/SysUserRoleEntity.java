package com.tdt.wheel.jwt.entity;


/**
 * @author tudoutiao
 * @version v1.0.0
 * @description: SysUserRoleEntity
 * @since 2020/07/11 17:33
 */
public class SysUserRoleEntity {
    /**
     * ID
     */
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 角色ID
     */
    private Long roleId;

    public Long getId() {
        return id;
    }

    public SysUserRoleEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public SysUserRoleEntity setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Long getRoleId() {
        return roleId;
    }

    public SysUserRoleEntity setRoleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }
}
