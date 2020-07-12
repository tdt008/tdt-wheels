package com.tdt.wheel.jwt.entity;


/**
 * @author tudoutiao
 * @version v1.0.0
 * @description: SysUserEntity
 * @since 2020/07/11 17:32
 */
public class SysUserEntity {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 状态:NORMAL正常  PROHIBIT禁用
     */
    private String status;

    public Long getUserId() {
        return userId;
    }

    public SysUserEntity setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public SysUserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SysUserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public SysUserEntity setStatus(String status) {
        this.status = status;
        return this;
    }
}
