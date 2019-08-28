package com.huangjie.sell.service.impl;


import com.huangjie.sell.dataobject.SellerInfo;
import com.huangjie.sell.service.SellerInfoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
/**
 * @author huangjie
 * @date 2019/08/20
 * @blame 黄杰
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoServiceImplTest {

    @Autowired
    private SellerInfoService sellerInfoService;
    @Test
    public void findByOpenId() {
        SellerInfo sellerInfo = sellerInfoService.findByOpenId("wx123456");
        Assert.assertNotNull(sellerInfo);
    }
}
