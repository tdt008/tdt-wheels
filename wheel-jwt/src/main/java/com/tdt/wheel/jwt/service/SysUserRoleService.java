package com.tdt.wheel.jwt.service;


import com.tdt.wheel.jwt.entity.SysRoleEntity;
import com.tdt.wheel.jwt.entity.SysUserRoleEntity;
import java.util.List;

/**
 * @Description 用户与角色业务接口
 */
public interface SysUserRoleService {

    List<SysRoleEntity> selectSysRoleByUserId(Long userId);

    void save(SysUserRoleEntity sysUserRoleEntity);
}