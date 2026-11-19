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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    public void testSelectByCondition() throws Exception {

        int status = 1;
        String companyName = "华为";
        String brandName = "华为";

        companyName = "%" +companyName+"%";
        brandName = "%"+brandName+"%";

        Brand brand = new Brand();
        brand.setStatus(status);
        brand.setCompanyName(companyName);
        brand.setBrandName(brandName);


        Map map = new HashMap();
        //map.put("status", status);
        map.put("companyName", companyName);
        //map.put("brandName", brandName);

        //1.加载配置文件，获取SqlSession对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);


        //2.获取对应的SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();


        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

//        List<Brand> brands = brandMapper.selectByCondition(brand);

        List<Brand> brands = brandMapper.selectByCondition(map);
        System.out.println(brands);

        sqlSession.close();

    }

    @Test
    public void testInsertBrand() throws Exception {
        int status = 1;
        String companyName = "香蕉";
        String brandName = "香蕉公司";


        Brand brand = new Brand();
        brand.setStatus(status);
        brand.setCompanyName(companyName);
        brand.setBrandName(brandName);
        brand.setDescription("大香蕉大香蕉");
        brand.setOrdered(1);


        //Map map = new HashMap();
        //map.put("status", status);
        //map.put("companyName", companyName);
        //map.put("brandName", brandName);

        //1.加载配置文件，获取SqlSession对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);


        //2.获取对应的SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();


        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

//        List<Brand> brands = brandMapper.selectByCondition(brand);

        int i = brandMapper.insertBrand(brand);
        System.out.println(i);
        sqlSession.commit();

        sqlSession.close();

    }

}
