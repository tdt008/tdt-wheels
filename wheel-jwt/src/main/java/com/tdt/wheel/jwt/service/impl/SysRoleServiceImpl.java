package com.tdt.wheel.jwt.service.impl;

import com.tdt.wheel.jwt.entity.SysRoleEntity;
import com.tdt.wheel.jwt.mapper.SysRoleMapper;
import com.tdt.wheel.jwt.service.SysRoleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 角色业务实现
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRoleEntity> list() {
        return sysRoleMapper.list();
    }
}