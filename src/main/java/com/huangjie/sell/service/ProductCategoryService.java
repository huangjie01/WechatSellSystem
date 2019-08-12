package com.huangjie.sell.service;


import com.huangjie.sell.dataobject.ProductCategory;

import java.util.List;

/**
 * Created by huangjie on 2019/7/18.
 */
public interface ProductCategoryService {

    ProductCategory findOne(Integer categoryID);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
