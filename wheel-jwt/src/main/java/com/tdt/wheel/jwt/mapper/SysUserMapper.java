package com.tdt.wheel.jwt.mapper;

import com.tdt.wheel.jwt.entity.SysMenuEntity;
import com.tdt.wheel.jwt.entity.SysRoleEntity;
import com.tdt.wheel.jwt.entity.SysUserEntity;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author tudoutiao
 * @version v1.0.0
 * @description: SysMenuDao
 * @since 2020/07/11 17:27
 */
@Mapper
public interface SysUserMapper {



    /**
     * @description 列表
     * @return java.util.List<com.tdt.wheel.jwt.entity.SysUserEntity>
     * @author tudoutiao
     * @date 22:47 2020/7/11
     **/
    @Select({"SELECT * FROM sys_user "})
    List<SysUserEntity> list();

    /**
     * @description  根据用户名查询
     * @param username
     * @return com.tdt.wheel.jwt.entity.SysUserEntity
     * @author tudoutiao
     * @date 10:53 2020/7/12
     **/
    @Select({"SELECT * FROM sys_user WHERE username=#{username} limit 1 "})
    SysUserEntity getByUserName(@Param("username") String username);

    @Insert({"INSERT INTO sys_user(username, password, status) values(#{username}, #{password}, #{status})"})
    @Options(useGeneratedKeys = true, keyColumn = "user_id", keyProperty = "userId")
    void save(SysUserEntity sysUserEntity);
}
