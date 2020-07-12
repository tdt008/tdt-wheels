package com.tdt.wheel.jwt.controller;

import com.tdt.wheel.jwt.entity.SysMenuEntity;
import com.tdt.wheel.jwt.entity.SysRoleEntity;
import com.tdt.wheel.jwt.entity.SysUserEntity;
import com.tdt.wheel.jwt.jwt.entity.SelfUserEntity;
import com.tdt.wheel.jwt.service.SysMenuService;
import com.tdt.wheel.jwt.service.SysRoleService;
import com.tdt.wheel.jwt.service.SysUserService;
import com.tdt.wheel.jwt.util.ResultUtil;
import com.tdt.wheel.jwt.util.SecurityUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 管理端
 * @author tudoutiao
 * @version v1.0.0
 * @since 2020/07/11 17:24
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 管理端信息
     * @Return Map<String,Object> 返回数据MAP
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/info")
    public Map<String, Object> userLogin() {
        Map<String, Object> result = new HashMap<>();
        SelfUserEntity userDetails = SecurityUtil.getUserInfo();
        result.put("title", "管理端信息");
        result.put("data", userDetails);
        return ResultUtil.resultSuccess(result);
    }

    /**
     * 拥有ADMIN或者USER角色可以访问
     * @Return Map<String,Object> 返回数据MAP
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping(value = "/list")
    public Map<String,Object> list() {
        Map<String, Object> result = new HashMap<>();
        List<SysUserEntity> sysUserEntityList = sysUserService.list();
        result.put("title","拥有用户或者管理员角色都可以查看");
        result.put("data",sysUserEntityList);
        return ResultUtil.resultSuccess(result);
    }

    /**
     * 拥有ADMIN和USER角色可以访问
     * @Return Map<String,Object> 返回数据MAP
     */
    @PreAuthorize("hasRole('ADMIN') and hasRole('USER')")
    @RequestMapping(value = "/menuList",method = RequestMethod.GET)
    public Map<String,Object> menuList(){
        Map<String,Object> result = new HashMap<>();
        List<SysMenuEntity> sysMenuEntityList = sysMenuService.list();
        result.put("title","拥有用户和管理员角色都可以查看");
        result.put("data", sysMenuEntityList);
        return ResultUtil.resultSuccess(result);
    }


    /**
     * 拥有sys:user:info权限可以访问
     * hasPermission 第一个参数是请求路径 第二个参数是权限表达式
     * @Return Map<String,Object> 返回数据MAP
     */
    @PreAuthorize("hasPermission('/admin/userList','sys:user:info')")
    @RequestMapping(value = "/userList",method = RequestMethod.GET)
    public Map<String,Object> userList(){
        Map<String,Object> result = new HashMap<>();
        List<SysUserEntity> sysUserEntityList = sysUserService.list();
        result.put("title","拥有sys:user:info权限都可以查看");
        result.put("data",sysUserEntityList);
        return ResultUtil.resultSuccess(result);
    }


    /**
     * 拥有ADMIN角色和sys:role:info权限可以访问
     * @Return Map<String,Object> 返回数据MAP
     */
    @PreAuthorize("hasRole('ADMIN') and hasPermission('/admin/adminRoleList','sys:role:info')")
    @RequestMapping(value = "/adminRoleList",method = RequestMethod.GET)
    public Map<String,Object> adminRoleList(){
        Map<String,Object> result = new HashMap<>();
        List<SysRoleEntity> sysRoleEntityList = sysRoleService.list();
        result.put("title","拥有ADMIN角色和sys:role:info权限可以访问");
        result.put("data",sysRoleEntityList);
        return ResultUtil.resultSuccess(result);
    }
}
