package com.huangjie.sell.service.impl;

import com.huangjie.sell.dataobject.ProductInfo;
import com.huangjie.sell.dto.CardDTO;
import com.huangjie.sell.enums.ProductStatusEnum;
import com.huangjie.sell.enums.ResultEnum;
import com.huangjie.sell.exception.SellException;
import com.huangjie.sell.repository.ProductInfoRepository;
import com.huangjie.sell.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by huangjie on 2019/7/19.
 */
@Service
@Slf4j
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public ProductInfo findOne(String productID) {
        return productInfoRepository.findOne(productID);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findAll();
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }


    @Override
    @Transactional(rollbackOn = Exception.class)
    public void increaseStock(List<CardDTO> cardDTOList) {
        for (CardDTO cardDTO : cardDTOList) {
            ProductInfo productInfo = findOne(cardDTO.getProductId());
            if (productInfo == null) {
                log.error("【商品不存在】CardDTO={}", cardDTO);
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
            }

            int count = productInfo.getProductStock() + cardDTO.getProductQuantity();
            productInfo.setProductStock(count);
            productInfoRepository.save(productInfo);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void decreaseStock(List<CardDTO> cardDTOList) {
        for (CardDTO cardDTO : cardDTOList) {
            ProductInfo productInfo = findOne(cardDTO.getProductId());
            if (productInfo == null) {
                log.error("【商品不存在】CardDTO={}", cardDTO);
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
            }
            int count = productInfo.getProductStock() - cardDTO.getProductQuantity();
            if (count < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(count);
            productInfoRepository.save(productInfo);
        }
    }

    @Override
    public ProductInfo onSale(String productId) {

        ProductInfo productInfo = productInfoRepository.findOne(productId);
        if (productInfo == null) {
            log.error("【上架商品 商品不存在】");
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
        }

        if (productInfo.getProductStatus().equals(ProductStatusEnum.UP.getCode())) {
            log.error("【上架商品 商品状态错误】");
            throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        return productInfoRepository.save(productInfo);
    }

    @Override
    public ProductInfo offSale(String productId) {

        ProductInfo productInfo = productInfoRepository.findOne(productId);
        if (productInfo == null) {
            log.error("【下架商品 商品不存在】");
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
        }

        if (productInfo.getProductStatus().equals(ProductStatusEnum.DOWN.getCode())) {
            log.error("【下架商品 商品状态错误】");
            throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return productInfoRepository.save(productInfo);

    }
}
