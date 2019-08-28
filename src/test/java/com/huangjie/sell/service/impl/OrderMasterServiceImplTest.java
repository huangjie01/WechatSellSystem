package com.huangjie.sell.service.impl;

import com.huangjie.sell.dataobject.OrderDetail;
import com.huangjie.sell.dto.OrderDTO;
import com.huangjie.sell.enums.OrderStatusEnum;
import com.huangjie.sell.enums.PayStatusEnum;
import com.huangjie.sell.service.OrderMasterService;
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
import java.util.Arrays;
import java.util.Date;

/**
 * Created by huangjie on 2019/7/21.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderMasterServiceImplTest {

    @Autowired
    private OrderMasterService orderMasterService;

    @Test
    public void testCreate() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("012345690");
        orderDTO.setBuyerAddress("上海市普普陀区");
        orderDTO.setBuyerName("黄先生");
        orderDTO.setBuyerOpenid("wx1234");
        orderDTO.setBuyerPhone("17702183630");
        orderDTO.setPayStatus(PayStatusEnum.FINISH.getCode());
        orderDTO.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderDTO.setCreateTime(new Date(System.currentTimeMillis()));
        orderDTO.setUpdateTime(new Date(System.currentTimeMillis()));
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId("012345679");
        orderDetail.setDetailId("01234");
        orderDetail.setProductQuantity(12);
        orderDetail.setProductPrice(new BigDecimal(45.5));
        orderDetail.setProductIcon("xxx.png");
        orderDetail.setProductName("哈根达斯");
        orderDetail.setProductId("0987654");
        orderDTO.setOrderDetailList(Arrays.asList(orderDetail));
        orderMasterService.create(orderDTO);
    }

    @Test
    public void testFindOne() throws Exception {
        OrderDTO one = orderMasterService.findOne("1563718150714822845");
        System.out.println(one.toString());
    }

    @Test
    public void testFindList() throws Exception {
        Pageable pageable = new PageRequest(0, 10);
        Page<OrderDTO> page = orderMasterService.findList("wx1234", pageable);
        Assert.assertEquals(page.getTotalElements(), 4);
    }

    @Test
    public void testCancel() throws Exception {
        OrderDTO one = orderMasterService.findOne("09123456");
        orderMasterService.cancel(one);

    }

    @Test
    public void testFinish() throws Exception {
        OrderDTO one = orderMasterService.findOne("09123456");
        orderMasterService.finish(one);
        Assert.assertEquals(one.getOrderStatus(), OrderStatusEnum.FINISH.getCode());
    }

    @Test
    public void testPaid() throws Exception {
        OrderDTO one = orderMasterService.findOne("09123456");
        OrderDTO paid = orderMasterService.paid(one);
        Assert.assertEquals(paid.getPayStatus(), PayStatusEnum.FINISH.getCode());
    }

    @Test
    public void testFindList1() throws Exception {
        Pageable pageable = new PageRequest(0, 10);
        Page<OrderDTO> list = orderMasterService.findList(pageable);
        Assert.assertEquals(10,list.getTotalElements());
    }


    @Test
    public void testPayStatusEnum(){
           OrderDTO orderDTO=new OrderDTO();
        orderDTO.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderDTO.setOrderStatus(OrderStatusEnum.NEW.getCode());

        System.out.println(orderDTO.getOrderStatusEnum().getMsg());
    }
}