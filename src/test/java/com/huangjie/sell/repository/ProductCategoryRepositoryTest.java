package com.huangjie.sell.repository;

import com.huangjie.sell.dataobject.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by huangjie on 2019/7/17.
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductCategoryRepositoryTest {
    @Autowired
    ProductCategoryRepository repository;


    @Test
    public void testSelect() {
        ProductCategory productCategory = repository.findOne(2);
        System.out.println(productCategory.toString());
    }

    @Test
    public void testSave() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("女生最爱");
        productCategory.setCategoryType(2);
        productCategory.setCreateTime(new Date(System.currentTimeMillis()));
        productCategory.setUpdateTime(new Date(System.currentTimeMillis()));
        ProductCategory save = repository.save(productCategory);
        System.out.println(save.toString());
    }

    @Test
    public void testUpdate() {
        ProductCategory save = repository.findOne(2);
        save.setCategoryType(100);
        repository.save(save);
    }


    @Test
    public void testFindCategoryTypeIn() {
        List<Integer> categoryTypeList = new ArrayList<>();
        categoryTypeList.add(1);
        List<ProductCategory> productCategoryList = repository.findByCategoryTypeIn(categoryTypeList);
        System.out.println(productCategoryList.toString());

    }
}