package com.huangjie.sell.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.huangjie.sell.dataobject.OrderDetail;
import com.huangjie.sell.enums.OrderStatusEnum;
import com.huangjie.sell.enums.PayStatusEnum;
import com.huangjie.sell.utils.EnumUtils;
import com.huangjie.sell.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by huangjie on 2019/7/20.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL) //这个注解的作用就是当返回的参数中的字段为空的时候，不返回该字段
public class OrderDTO {
    /**
     * 订单id.
     */
    private String orderId;

    /**
     * 买家名字.
     */
    private String buyerName;

    /**
     * 买家手机号.
     */
    private String buyerPhone;

    /**
     * 买家地址.
     */
    private String buyerAddress;

    /**
     * 买家微信Openid.
     */
    private String buyerOpenid;

    /**
     * 订单总金额.
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态, 默认为0新下单.
     */
    private Integer orderStatus;

    /**
     * 支付状态, 默认为0未支付.
     */
    private Integer payStatus;

    /**
     * 创建时间.
     */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    /**
     * 更新时间.
     */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    private List<OrderDetail> orderDetailList;

    /**
     * 获取订单状态的枚举
     *
     * @return
     */
    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum() {
        return EnumUtils.getByCode(orderStatus, OrderStatusEnum.class);
    }

    /**
     * 获取支付状态枚举
     *
     * @return
     */
    @JsonIgnore //转json 的时候忽略
    public PayStatusEnum getPayStatusEnum() {
        return EnumUtils.getByCode(payStatus, PayStatusEnum.class);
    }


}
