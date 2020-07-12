package com.tdt.wheel.jwt.mapper;

import com.tdt.wheel.jwt.entity.SysRoleEntity;
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
public interface SysRoleMapper {

    @Select({"SELECT * FROM sys_role"})
    List<SysRoleEntity> list();
}
