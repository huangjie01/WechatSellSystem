package com.huangjie.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huangjie.sell.dataobject.OrderDetail;
import com.huangjie.sell.dto.OrderDTO;
import com.huangjie.sell.enums.ResultEnum;
import com.huangjie.sell.exception.SellException;
import com.huangjie.sell.form.OrderForm;
import com.huangjie.sell.utils.KeyUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;

/**
 * Created by huangjie on 2019/7/22.
 */
@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        orderDTO.setOrderId(KeyUtils.genUniqueKey());
        orderDTO.setCreateTime(new Date(System.currentTimeMillis()));
        orderDTO.setUpdateTime(new Date(System.currentTimeMillis()));
        List<OrderDetail> orderDetailList = null;
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());
        } catch (Exception e) {
            log.error("【json 转换对象错误】String={}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
