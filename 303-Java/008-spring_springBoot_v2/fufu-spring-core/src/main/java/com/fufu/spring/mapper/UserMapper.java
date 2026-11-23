package com.fufu.spring.mapper;

import com.fufu.spring.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Spring 整合 MyBatis 验证用 Mapper（由 MapperScannerConfigurer 自动扫描注册为代理 Bean）
 */
public interface UserMapper {

    @Select("SELECT id, username, password, gender, addr FROM tb_user")
    List<User> selectAll();
}
