package com.tdt.wheel.jwt.service.impl;

import com.tdt.wheel.jwt.entity.SysRoleEntity;
import com.tdt.wheel.jwt.entity.SysUserRoleEntity;
import com.tdt.wheel.jwt.mapper.SysUserRoleMapper;
import com.tdt.wheel.jwt.service.SysUserRoleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 用户与角色业务实现
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public List<SysRoleEntity> selectSysRoleByUserId(Long userId) {
        return sysUserRoleMapper.selectSysRoleByUserId(userId);
    }

    @Override
    public void save(SysUserRoleEntity sysUserRoleEntity) {
        sysUserRoleMapper.save(sysUserRoleEntity);
    }
}