package com.huangjie.sell.service;

import com.huangjie.sell.dataobject.SellerInfo;

/**
 * @author huangjie
 * @date 2019/08/19
 * @blame 黄杰
 **/
public interface SellerInfoService {
    SellerInfo findByOpenId(String openId);
}
