package com.huangjie.sell.service.impl;

import com.huangjie.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by huangjie on 2019/7/18.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductCategoryServiceImplTest {

    @Autowired
    private ProductCategoryServiceImpl productCategoryService;
    @Test
    public void testFindOne() throws Exception {
        ProductCategory one = productCategoryService.findOne(6);
        Assert.assertNotNull(one);
    }

    @Test
    public void testFindAll() throws Exception {
        List<ProductCategory> all = productCategoryService.findAll();
        Assert.assertNotNull(all);
    }

    @Test
    public void testFindByCategoryTypeIn() throws Exception {
        List<Integer> categoryTypeList=new ArrayList<>();
        categoryTypeList.add(1);
        categoryTypeList.add(2);
        List<ProductCategory> byCategoryTypeIn = productCategoryService.findByCategoryTypeIn(categoryTypeList);
         Assert.assertNotNull(byCategoryTypeIn);
    }

    @Test
    public void testSave() throws Exception {
         ProductCategory productCategory=new ProductCategory();
        productCategory.setCategoryType(3);
        productCategory.setCategoryName("儿童最爱");
        productCategory.setCreateTime(new Date(System.currentTimeMillis()));
        productCategory.setUpdateTime(new Date(System.currentTimeMillis()));
        productCategoryService.save(productCategory);
    }
}