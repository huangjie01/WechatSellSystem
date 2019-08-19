package com.huangjie.sell.repository;


import com.huangjie.sell.dataobject.SellerInfo;
import com.huangjie.sell.utils.KeyUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoRepositoryTest {
    @Autowired
    private SellerInfoRepository sellerInfoRepository;
    @Test
    public void findByOpenId() {
        SellerInfo sellerInfo = sellerInfoRepository.findByOpenId("wx123456");
        Assert.assertNotNull(sellerInfo);
    }

    @Test
    public void testSave(){
        SellerInfo sellerInfo=new SellerInfo();
        sellerInfo.setId(KeyUtils.genUniqueKey());
        sellerInfo.setUserName("hj");
        sellerInfo.setPassWord("hj123");
        sellerInfo.setOpenId("wx123456");
        sellerInfo.setCreateTime(new Date(System.currentTimeMillis()));
        sellerInfo.setUpdateTime(new Date(System.currentTimeMillis()));
        sellerInfoRepository.save(sellerInfo);
    }
}
