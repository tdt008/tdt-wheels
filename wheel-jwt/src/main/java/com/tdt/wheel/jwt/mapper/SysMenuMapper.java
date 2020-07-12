package com.tdt.wheel.jwt.mapper;

import com.tdt.wheel.jwt.entity.SysMenuEntity;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author tudoutiao
 * @version v1.0.0
 * @description: SysMenuDao
 * @since 2020/07/11 17:27
 */
@Mapper
public interface SysMenuMapper {

    /**
     * 通过用户ID查询权限集合
     * @Param  userId 用户ID
     * @Return List<SysMenuEntity> 角色名集合
     */
    @Select({"SELECT sr.* FROM sys_role sr "
            + " LEFT JOIN sys_user_role se ON se.role_id = sr.role_id "
            + " WHERE se.user_id = #{userId}"})
    List<SysMenuEntity> selectSysMenuByUserId(Long userId);

    @Select({"SELECT * FROM sys_menu"})
    List<SysMenuEntity> list();
}
