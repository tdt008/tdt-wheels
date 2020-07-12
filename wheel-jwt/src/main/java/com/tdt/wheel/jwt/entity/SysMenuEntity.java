package com.tdt.wheel.jwt.entity;


/**
 * @author tudoutiao
 * @version v1.0.0
 * @description: SysMenuEntity
 * @since 2020/07/11 17:30
 */
public class SysMenuEntity {
    /**
     * 权限ID
     */
    private Long menuId;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 权限标识
     */
    private String permission;

    public Long getMenuId() {
        return menuId;
    }

    public SysMenuEntity setMenuId(Long menuId) {
        this.menuId = menuId;
        return this;
    }

    public String getName() {
        return name;
    }

    public SysMenuEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getPermission() {
        return permission;
    }

    public SysMenuEntity setPermission(String permission) {
        this.permission = permission;
        return this;
    }
}
