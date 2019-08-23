package com.huangjie.sell.repository;

import com.huangjie.sell.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerInfoRepository extends JpaRepository<SellerInfo,String> {

     SellerInfo findByOpenId(String openId);
}
