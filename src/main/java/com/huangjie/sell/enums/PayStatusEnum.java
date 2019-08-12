package com.huangjie.sell.enums;


/**
 * Created by huangjie on 2019/7/19.
 */
public enum PayStatusEnum implements CodeEnum<Integer> {
    WAIT(0, "等待支付"),
    FINISH(1, "支付完成"),
    FAIL(-1, "支付失败");
    private Integer code;
    private String msg;

    @Override
    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
