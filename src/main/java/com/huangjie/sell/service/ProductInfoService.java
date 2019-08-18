package com.huangjie.sell.service;

import com.huangjie.sell.dataobject.ProductInfo;
import com.huangjie.sell.dto.CardDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by huangjie on 2019/7/18.
 */
public interface ProductInfoService {
    ProductInfo findOne(String productID);

    /**
     * 查询上架的所有商品
     */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //加库存

    void increaseStock(List<CardDTO> cardDTOList);

    //减库存
    void decreaseStock(List<CardDTO> cardDTOList);


    ProductInfo onSale(String productId);

    ProductInfo offSale(String productId);
}

