package com.huangjie.sell.dataobject.mapper;

import com.huangjie.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author huangjie
 * @date 2019/08/28
 * @blame 黄杰
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductCategoryMapperTest {
    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    public void insertByMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("category_name", "程序员最不喜欢");
        map.put("category_type", 101);
        int result = mapper.insertByMap(map);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void insertByObject() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("程序员最爱");
        productCategory.setCategoryType(1);
        int result = mapper.insertByObject(productCategory);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void findByCategoryType() {
        ProductCategory productCategory = mapper.findByCategoryType(1);
        Assert.assertNotNull(productCategory);
    }

    @Test
    public void findAllProductCategory() {
        List<ProductCategory> allProductCategory = mapper.findAllProductCategory();
        System.out.println(allProductCategory.toString());
        Assert.assertEquals(allProductCategory.size(),3);
    }

    @Test
    public void findProductCategoryByName() {
        ProductCategory productCategory = mapper.findProductCategoryByName("程序员最爱");
        System.out.println(productCategory);
    }

    @Test
    public void updateByCategoryType() {
       int count=  mapper.updateByCategoryType("程序员必备",2);
        System.out.println(count);
    }

    @Test
    public void updateByObject() {
        ProductCategory productCategory=new ProductCategory();
        productCategory.setCategoryName("程序媛比备");
        productCategory.setCategoryType(2);
        productCategory.setCreateTime(new Date(System.currentTimeMillis()));
        productCategory.setUpdateTime(new Date(System.currentTimeMillis()));
      int count=  mapper.updateByObject(productCategory);
        System.out.println(count);
    }
}
