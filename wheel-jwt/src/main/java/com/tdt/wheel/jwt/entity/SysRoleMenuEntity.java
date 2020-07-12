package com.tdt.wheel.jwt.entity;


/**
 * @author tudoutiao
 * @version v1.0.0
 * @description: SysRoleMenuEntity
 * @since 2020/07/11 17:32
 */
public class SysRoleMenuEntity {
    /**
     * ID
     */
    private Long id;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 权限ID
     */
    private Long menuId;

    public Long getId() {
        return id;
    }

    public SysRoleMenuEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getRoleId() {
        return roleId;
    }

    public SysRoleMenuEntity setRoleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }

    public Long getMenuId() {
        return menuId;
    }

    public SysRoleMenuEntity setMenuId(Long menuId) {
        this.menuId = menuId;
        return this;
    }
}
