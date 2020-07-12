package com.tdt.wheel.jwt.service.impl;

import com.tdt.wheel.jwt.entity.SysMenuEntity;
import com.tdt.wheel.jwt.entity.SysRoleEntity;
import com.tdt.wheel.jwt.entity.SysUserEntity;
import com.tdt.wheel.jwt.mapper.SysUserMapper;
import com.tdt.wheel.jwt.service.SysMenuService;
import com.tdt.wheel.jwt.service.SysUserRoleService;
import com.tdt.wheel.jwt.service.SysUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 系统用户业务实现
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * @description 列表
     * @return java.util.List<com.tdt.wheel.jwt.entity.SysUserEntity>
     * @author tudoutiao
     * @date 22:47 2020/7/11
     **/
    @Override
    public List<SysUserEntity> list() {
        return sysUserMapper.list();
    }

    /**
     * @description 根据用户名查询
     * @param username
     * @return com.tdt.wheel.jwt.entity.SysUserEntity
     * @author tudoutiao
     * @date 22:48 2020/7/11
     **/
    @Override
    public SysUserEntity selectUserByName(String username) {
        return sysUserMapper.getByUserName(username);
    }


    /**
     * @description 通过用户ID查询角色集合
     * @param userId
     * @return java.util.List<com.tdt.wheel.jwt.entity.SysRoleEntity>
     * @author tudoutiao
     * @date 22:49 2020/7/11
     **/
    @Override
    public List<SysRoleEntity> selectSysRoleByUserId(Long userId) {
        return sysUserRoleService.selectSysRoleByUserId(userId);
    }

    /**
     * @description 根据用户ID查询权限集合
     * @param userId
     * @return java.util.List<com.tdt.wheel.jwt.entity.SysMenuEntity>
     * @author tudoutiao
     * @date 22:49 2020/7/11
     **/
    @Override
    public List<SysMenuEntity> selectSysMenuByUserId(Long userId) {
        return sysMenuService.selectSysMenuByUserId(userId);
    }

    @Override
    public void save(SysUserEntity sysUserEntity) {
        sysUserMapper.save(sysUserEntity);
    }
}