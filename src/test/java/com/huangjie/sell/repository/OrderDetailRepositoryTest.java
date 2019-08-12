package com.huangjie.sell.repository;

import com.huangjie.sell.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by huangjie on 2019/7/20.
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void testFindByOrderId() throws Exception {
        List<OrderDetail> byOrderId = orderDetailRepository.findByOrderId("09123456");
        Assert.assertEquals(byOrderId.size(),1);
    }


    @Test
    public void testSave() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId("09123456");
        orderDetail.setDetailId("01233458");
        orderDetail.setProductIcon("http://img.hqew.com/File/Images/0-9999/0/HR/2017425181741594802.jpg");
        orderDetail.setProductId("0123456");
        orderDetail.setProductName("MacBook Pro");
        orderDetail.setProductPrice(new BigDecimal(2100.50));
        orderDetail.setProductQuantity(1);
        orderDetailRepository.save(orderDetail);
    }

}