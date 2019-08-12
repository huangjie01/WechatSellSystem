package com.huangjie.sell.utils;

import com.huangjie.sell.enums.CodeEnum;

/**
 * Created by huangjie on 2019/7/23.
 */
public class EnumUtils {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {

        for (T each : enumClass.getEnumConstants()) {
            if (each.getCode() == code) {
                return each;
            }
        }
        return null;
    }
}
