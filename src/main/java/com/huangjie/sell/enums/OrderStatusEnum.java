package com.huangjie.sell.enums;

/**
 * Created by huangjie on 2019/7/19.
 */
public enum OrderStatusEnum  implements CodeEnum<Integer>{
    NEW(1, "新订单"),
    FINISH(2, "已完成"),
    CANCEL(-1, "取消");

    private Integer code;
    private String msg;

    @Override
    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
