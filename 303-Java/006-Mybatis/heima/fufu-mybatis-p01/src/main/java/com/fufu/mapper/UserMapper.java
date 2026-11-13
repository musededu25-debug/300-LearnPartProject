package com.fufu.mapper;

import com.fufu.pojo.User;

import java.util.List;

public interface UserMapper {

    @SuppressWarnings("MybatisXMapperMethodInspection")
    public List<User> selectAll();

}
