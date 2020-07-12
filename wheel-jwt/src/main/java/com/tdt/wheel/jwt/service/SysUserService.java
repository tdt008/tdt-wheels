package com.tdt.wheel.jwt.service;


import com.tdt.wheel.jwt.entity.SysMenuEntity;
import com.tdt.wheel.jwt.entity.SysRoleEntity;
import com.tdt.wheel.jwt.entity.SysUserEntity;
import java.util.List;

/**
 * @Description 系统用户业务接口
 */
public interface SysUserService {

    /**
     * 根据用户名查询实体
     * @Param  username 用户名
     * @Return SysUserEntity 用户实体
     */
    SysUserEntity selectUserByName(String username);
    /**
     * 根据用户ID查询角色集合
     * @Param  userId 用户ID
     * @Return List<SysRoleEntity> 角色名集合
     */
    List<SysRoleEntity> selectSysRoleByUserId(Long userId);
    /**
     * 根据用户ID查询权限集合
     * @Param  userId 用户ID
     * @Return List<SysMenuEntity> 角色名集合
     */
    List<SysMenuEntity> selectSysMenuByUserId(Long userId);

    /**
     * @description 列表
     * @return java.util.List<com.tdt.wheel.jwt.entity.SysUserEntity>
     * @author tudoutiao
     * @date 22:47 2020/7/11
     **/
    List<SysUserEntity> list();

    void save(SysUserEntity sysUserEntity);
}