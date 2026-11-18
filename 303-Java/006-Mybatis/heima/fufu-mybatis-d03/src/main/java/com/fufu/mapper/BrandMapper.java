package com.fufu.mapper;

import com.fufu.pojo.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandMapper {

    public List<Brand> selectAllBrand();

    public Brand selectBrandById(@Param("id") int id);

}
