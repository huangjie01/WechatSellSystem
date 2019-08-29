package com.huangjie.sell.dataobject.mapper;

import com.huangjie.sell.dataobject.ProductCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author huangjie
 * @date 2019/08/28
 * @blame 黄杰
 **/
public interface ProductCategoryMapper {
    @Insert("insert into product_category(category_name,category_type) values(#{category_name,jdbcType=VARCHAR},#{category_type,jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);

    @Insert("INSERT INTO product_category(category_name,category_type) values(#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=INTEGER})")
    int insertByObject(ProductCategory productCategory);


    @Select("SELECT * FROM product_category where category_type= #{categoryType}")
    @Results(
            {
                    @Result(column = "category_type", property = "categoryType"),
                    @Result(column = "category_name", property = "categoryName"),
                    @Result(column = "update_time", property = "updateTime"),
                    @Result(column = "create_time", property = "createTime"),
                    @Result(column = "category_id", property = "categoryId")
            }
    )
    ProductCategory findByCategoryType(int categoryType);


    @Select("SELECT * FROM product_category")
    @Results(
            {
                    @Result(column = "category_type", property = "categoryType"),
                    @Result(column = "category_name", property = "categoryName"),
                    @Result(column = "update_time", property = "updateTime"),
                    @Result(column = "create_time", property = "createTime"),
                    @Result(column = "category_id", property = "categoryId")
            }
    )
    List<ProductCategory> findAllProductCategory();

    @Select("SELECT * FROM product_category where category_name= #{categoryName}")
    @Results(
            {
                    @Result(column = "category_type", property = "categoryType"),
                    @Result(column = "category_name", property = "categoryName"),
                    @Result(column = "update_time", property = "updateTime"),
                    @Result(column = "create_time", property = "createTime"),
                    @Result(column = "category_id", property = "categoryId")
            }
    )

    ProductCategory findProductCategoryByName(String categoryName);
    @Update("update product_category set category_name=#{categoryName} where category_type= #{categoryType}")
    int updateByCategoryType(@Param("categoryName") String categoryName,@Param("categoryType") Integer categoryType);


    @Update("update product_category set category_name=#{categoryName},create_time=#{createTime},update_time=#{updateTime} where category_type= #{categoryType}")
    int updateByObject(ProductCategory productCategory);
}
