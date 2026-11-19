package com.fufu.mapper;

import com.fufu.pojo.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BrandMapper {

    public List<Brand> selectAllBrand();

    public Brand selectBrandById(@Param("id") int id);

    //public Brand selectByCondition(@Param("status")int status,@Param("companyName")String companyName,@Param("brandName")String brandName);

//    public List<Brand> selectByCondition(Brand brand);

    public List<Brand> selectByCondition(Map map);

    public List<Brand> selectByConditionSingle(Brand brand);


    public int insertBrand(Brand brand);

}
