package com.huangjie.sell.controller;

import com.huangjie.sell.VO.ProductInfoVO;
import com.huangjie.sell.VO.ProductVO;
import com.huangjie.sell.VO.ResultVO;
import com.huangjie.sell.dataobject.ProductCategory;
import com.huangjie.sell.dataobject.ProductInfo;
import com.huangjie.sell.service.ProductCategoryService;
import com.huangjie.sell.service.ProductInfoService;
import com.huangjie.sell.utils.ResultVOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by huangjie on 2019/7/19.
 */
@RestController
@RequestMapping("buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private ProductCategoryService categoryService;

    @GetMapping("list")
    public ResultVO list() {
        //查询所有的上架的商品列表
        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        List<Integer> categoryList = productInfoList.stream().map(e ->
                e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryList);
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType() == productCategory.getCategoryType()) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtils.success(productVOList);
    }
}
