package com.huangjie.sell.repository;

import com.huangjie.sell.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by huangjie on 2019/7/18.
 */

@Repository
public interface ProductInfoRepository  extends JpaRepository<ProductInfo,String> {

    /** 根据商品状态查询*/
    List<ProductInfo> findByProductStatus(Integer productInfoStatus);
}
