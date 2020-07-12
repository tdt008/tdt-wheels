package com.tdt.wheel.jwt.jwt.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.io.Serializable;
import java.util.Collection;

/**
 * SpringSecurity用户的实体
 * 注意:这里必须要实现UserDetails接口
 */
public class SelfUserEntity implements Serializable, UserDetails {

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


	/**
	 * 用户角色
	 */
	private Collection<GrantedAuthority> authorities;
	/**
	 * 账户是否过期
	 */
	private boolean isAccountNonExpired = false;
	/**
	 * 账户是否被锁定
	 */
	private boolean isAccountNonLocked = false;
	/**
	 * 证书是否过期
	 */
	private boolean isCredentialsNonExpired = false;
	/**
	 * 账户是否有效
	 */
	private boolean isEnabled = true;


	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}
	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}
	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}
	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

	public Long getUserId() {
		return userId;
	}

	public SelfUserEntity setUserId(Long userId) {
		this.userId = userId;
		return this;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public SelfUserEntity setUsername(String username) {
		this.username = username;
		return this;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public SelfUserEntity setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public SelfUserEntity setStatus(String status) {
		this.status = status;
		return this;
	}

	public SelfUserEntity setAuthorities(
			Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
		return this;
	}

	public SelfUserEntity setAccountNonExpired(boolean accountNonExpired) {
		isAccountNonExpired = accountNonExpired;
		return this;
	}

	public SelfUserEntity setAccountNonLocked(boolean accountNonLocked) {
		isAccountNonLocked = accountNonLocked;
		return this;
	}

	public SelfUserEntity setCredentialsNonExpired(boolean credentialsNonExpired) {
		isCredentialsNonExpired = credentialsNonExpired;
		return this;
	}

	public SelfUserEntity setEnabled(boolean enabled) {
		isEnabled = enabled;
		return this;
	}
}