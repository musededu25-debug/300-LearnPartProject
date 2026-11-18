package com.fufu;

import com.fufu.mapper.BrandMapper;
import com.fufu.pojo.Brand;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class BrandMapperTest {

    @Test
    public void testSelectAll() throws Exception {
        //1.加载配置文件，获取SqlSession对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);


        //2.获取对应的SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();


        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        List<Brand> brands = brandMapper.selectAllBrand();

        System.out.println(brands);

        sqlSession.close();

    }


    @Test
    public void testSelectById() throws Exception {
        //1.加载配置文件，获取SqlSession对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);


        //2.获取对应的SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();


        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        Brand brand = brandMapper.selectBrandById(1);

        System.out.println(brand);

        sqlSession.close();

    }

}
