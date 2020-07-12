package com.tdt.wheel.jwt.service;


import com.tdt.wheel.jwt.entity.SysMenuEntity;
import java.util.List;

/**
 * @Description 权限业务接口
 */
public interface SysMenuService {


    List<SysMenuEntity> selectSysMenuByUserId(Long userId);

    List<SysMenuEntity> list();
}