package com.tdt.wheel.jwt.mapper;

import com.tdt.wheel.jwt.entity.SysRoleEntity;
import com.tdt.wheel.jwt.entity.SysUserRoleEntity;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * @author tudoutiao
 * @version v1.0.0
 * @description: SysMenuDao
 * @since 2020/07/11 17:27
 */
@Mapper
public interface SysUserRoleMapper {

    /**
     * 通过用户ID查询角色集合
     * @CreateTime 2019/9/18 18:01
     * @Param  userId 用户ID
     * @Return List<SysRoleEntity> 角色名集合
     */
    @Select({"SELECT DISTINCT m.* FROM sys_user_role ur "
            + " LEFT JOIN sys_role_menu rm ON ur.role_id = rm.role_id "
            + " LEFT JOIN sys_menu m ON rm.menu_id = m.menu_id "
            + "  WHERE ur.user_id = #{userId}"})
    List<SysRoleEntity> selectSysRoleByUserId(Long userId);

    @Insert({"INSERT INTO sys_user_role(user_id, role_id) VALUES(#{userId}, #{roleId})"})
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void save(SysUserRoleEntity sysUserRoleEntity);
}
