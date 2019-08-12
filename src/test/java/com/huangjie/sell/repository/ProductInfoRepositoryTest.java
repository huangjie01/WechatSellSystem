package com.huangjie.sell.repository;

import com.huangjie.sell.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by huangjie on 2019/7/18.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Test
    public void testFindByProductStatus() throws Exception {

        List<ProductInfo> byProductStatus = productInfoRepository.findByProductStatus(1);
        Assert.assertNotNull(byProductStatus);
    }

    @Test
    public void testSave(){
        ProductInfo productInfo=new ProductInfo();
        productInfo.setProductId("0987654");
        productInfo.setCategoryType(2);
        productInfo.setProductName("哈根达斯");
        productInfo.setProductDescription("女生非常喜欢吃");
        productInfo.setProductIcon("http://img.hqew.com/File/Images/0-9999/0/HR/2017425181741594802.jpg");
        productInfo.setProductPrice(new BigDecimal(45.5));
        productInfo.setProductStatus(1);
        productInfo.setProductStock(1000);
        productInfo.setCreateTime(new Date(System.currentTimeMillis()));
        productInfo.setUpdateTime(new Date(System.currentTimeMillis()));
        ProductInfo save = productInfoRepository.save(productInfo);
        Assert.assertNotNull(save);
    }


}