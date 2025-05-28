package com.tdt.springmybatis.mapper;

import com.tdt.springmybatis.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    User getById(@Param("id") Long id);
}
