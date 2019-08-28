package com.huangjie.sell.dataobject.mapper;

import com.huangjie.sell.dataobject.ProductCategory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author huangjie
 * @date 2019/08/28
 * @blame 黄杰
 **/
public interface ProductCategoryMapper {
    @Insert("insert into product_category(category_name,category_type) values(#{category_name,jdbcType=VARCHAR},#{category_type,jdbcType=INTEGER})")
    int insertByMap(Map<String,Object> map);

    @Insert("INSERT INTO product_category(category_name,category_type) values(#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=INTEGER})")
    int insertByObject(ProductCategory productCategory);


    @Select("SELECT * FROM product_category where category_type=#{categoryType}")
    ProductCategory findByCategoryType(Integer categoryType);

    @Select("SELECT * FROM product_category")
    List<ProductCategory> findAllProductCategory();
}
