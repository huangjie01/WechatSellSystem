package com.huangjie.sell.utils;

import java.util.Random;

/**
 * Created by huangjie on 2019/7/21.
 */
public class KeyUtils {

    /**
     * 生成时间戳+6位随机数的主键
     *
     * @return
     */
    public static synchronized String genUniqueKey() {

        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
