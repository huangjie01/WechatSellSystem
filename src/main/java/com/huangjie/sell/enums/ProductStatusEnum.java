package com.huangjie.sell.enums;

import lombok.Getter;

/**
 * Created by huangjie on 2019/7/19.
 */

public enum ProductStatusEnum {
    UP(0, "在架"),
    DOWN(1, "下架");
    private Integer code;
    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }
}