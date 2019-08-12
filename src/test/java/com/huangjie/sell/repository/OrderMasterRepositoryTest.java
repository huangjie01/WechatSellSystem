package com.huangjie.sell.repository;

import com.huangjie.sell.dataobject.OrderMaster;
import com.huangjie.sell.enums.OrderStatusEnum;
import com.huangjie.sell.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by huangjie on 2019/7/20.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderMasterRepositoryTest {
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void testFindByBuyerOpenid() throws Exception {
        Pageable pageable = new PageRequest(0, 5);
        Page<OrderMaster> orderPage = orderMasterRepository.findByBuyerOpenid("wx123456", pageable);
        Assert.assertEquals(orderPage.getTotalElements(),1);
    }


    @Test
    public void testSave() {

        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("09123456");
        orderMaster.setBuyerAddress("上海市普陀区长征家苑1701");
        orderMaster.setBuyerName("黄爸爸");
        orderMaster.setBuyerPhone("1621535311");
        orderMaster.setBuyerOpenid("wx123456");
        orderMaster.setOrderAmount(new BigDecimal(12));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.FINISH.getCode());
        orderMaster.setUpdateTime(new Date(System.currentTimeMillis()));
        orderMaster.setCreateTime(new Date(System.currentTimeMillis()));
        orderMasterRepository.save(orderMaster);

    }
}