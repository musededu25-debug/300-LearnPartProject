package com.fufu.mapper;

import com.fufu.pojo.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandMapper {

    //1.查询所有
    List<Brand> selectAll();
    //2.根据id查询详情

    Brand selectById(@Param("id") Integer id);
    //3.新增品牌
    int insertBrand(Brand brand);

    //4.根据id修改全部字段
    int updateBrand(Brand brand);

    //5.根据id删除
    int deleteById(@Param("id")Integer id);
}
