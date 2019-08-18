package com.huangjie.sell.service.impl;

import com.huangjie.sell.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by huangjie on 2019/7/19.
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductInfoServiceImplTest {
    @Autowired
    ProductInfoServiceImpl productInfoService;

    @Test
    public void testFindOne() throws Exception {
        ProductInfo one = productInfoService.findOne("0123456");
        Assert.assertNotNull(one);
    }

    @Test
    public void testFindUpAll() throws Exception {

    }

    @Test
    public void testFindAll() throws Exception {

        Pageable pageable = new PageRequest(0, 10);
        Page<ProductInfo> all = productInfoService.findAll(pageable);
        List<ProductInfo> content = all.getContent();
        ProductInfo productInfo = content.get(0);
        productInfo.getProductStatusEnum().getMessage();
        System.out.println(productInfo.getProductStatusEnum().getMessage());
        System.out.println(all.getTotalElements());
    }

    @Test
    public void testSave() throws Exception {

    }


    @Test
    public void testOffSale(){
        ProductInfo productInfo=productInfoService.findOne("");
    }
}