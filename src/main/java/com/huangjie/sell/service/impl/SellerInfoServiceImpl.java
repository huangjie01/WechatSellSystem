package com.huangjie.sell.service.impl;

import com.huangjie.sell.dataobject.SellerInfo;
import com.huangjie.sell.repository.SellerInfoRepository;
import com.huangjie.sell.service.SellerInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huangjie
 * @date 2019/08/19
 * @blame 黄杰
 **/
@Service
@Slf4j
public class SellerInfoServiceImpl implements SellerInfoService {
    @Autowired
    private SellerInfoRepository sellerInfoRepository;
    @Override
    public SellerInfo findByOpenId(String openId) {
        return sellerInfoRepository.findByOpenId(openId);
    }
}
