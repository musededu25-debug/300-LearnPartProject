package com.fufu.mapper;

import com.fufu.pojo.User;

import java.util.List;

@SuppressWarnings("MybatisXMapperMethodInspection")
public interface UserMapper {

    public List<User> selectAll();
}
