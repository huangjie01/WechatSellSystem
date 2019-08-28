package com.huangjie.sell.repository;

import com.huangjie.sell.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author huangjie
 * @version 2019/8/23 17:48
 * @blame 黄杰
 */
@Repository
public interface SellerInfoRepository extends JpaRepository<SellerInfo,String> {

    SellerInfo findByOpenId(String openId);
}
