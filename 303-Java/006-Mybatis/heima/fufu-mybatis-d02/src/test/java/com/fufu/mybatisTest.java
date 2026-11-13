package com.fufu;

import com.fufu.mapper.UserMapper;
import com.fufu.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class mybatisTest {

    @Test
    public void test() throws Exception {

        //1.加载配置文件，获取SqlSession对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);


        //2.获取对应的SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();


        //3.获取对应UserMapper
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        List<User> users =userMapper.selectAll();

        System.out.println(users);
        sqlSession.close();

    }
}
