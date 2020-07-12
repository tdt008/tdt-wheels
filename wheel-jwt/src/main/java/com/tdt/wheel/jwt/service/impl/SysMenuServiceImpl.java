package com.tdt.wheel.jwt.service.impl;

import com.tdt.wheel.jwt.entity.SysMenuEntity;
import com.tdt.wheel.jwt.mapper.SysMenuMapper;
import com.tdt.wheel.jwt.service.SysMenuService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 权限业务实现
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenuEntity> selectSysMenuByUserId(Long userId) {
        return sysMenuMapper.selectSysMenuByUserId(userId);
    }

    @Override
    public List<SysMenuEntity> list() {
        return sysMenuMapper.list();
    }
}