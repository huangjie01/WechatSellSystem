package com.huangjie.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by huangjie on 2019/7/19.
 */
public class ProductVO {

    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("categoryType")
    private Integer categoryType;
    @JsonProperty("fooods")
    private List<ProductInfoVO> productInfoList;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }

    public List<ProductInfoVO> getProductInfoList() {
        return productInfoList;
    }

    public void setProductInfoList(List<ProductInfoVO> productInfoList) {
        this.productInfoList = productInfoList;
    }
}
