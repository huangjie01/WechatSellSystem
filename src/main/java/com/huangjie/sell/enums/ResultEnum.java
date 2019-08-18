package com.huangjie.sell.enums;

/**
 * Created by huangjie on 2019/7/20.
 */
public enum ResultEnum {

    SUCCESS(1,"成功"),
    ORDER_CANCEL_SUCCESS(2,"订单取消成功"),
    PARAM_ERROR(-10,"参数错误"),

    PRODUCT_NOT_EXIT(-1, "商品不存在"),
    CART_EMPTY(-9,"购物车为空"),
    PRODUCT_STOCK_ERROR(-2, "商品库存错误"),
    ORDER_NOT_EXIT(-3, "订单不存在"),
    ORDER_DETAIL_NOT_EXIT(-4, "订单详情不存在"),
    ORDER_STATUS_ERROR(-5, "订单状态错误"),
    ORDER_EMPTY_ERROR(-6, "订单详情为空"),
    ORDER_UPDATE_FAIL(-7, "更新订单状态错误"),
    ORDER_PAY_STATUS_ERROR(-8, "支付状态错误"),
    PRODUCT_STATUS_ERROR(-9,"商品状态错误");
    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
