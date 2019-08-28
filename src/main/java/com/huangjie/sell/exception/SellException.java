package com.huangjie.sell.exception;

import com.huangjie.sell.enums.ResultEnum;
import lombok.Getter;

/**
 * Created by huangjie on 2019/7/20.
 */
@Getter
public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public SellException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
