package com.huangjie.sell.dto;

import lombok.Data;

/**
 * 购物车
 * Created by huangjie on 2019/7/21.
 */
@Data
public class CardDTO {
    /**
     * 商品id
     */
    private String productId;
    /**
     * 商品数量
     */
    private Integer productQuantity;

    public CardDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
