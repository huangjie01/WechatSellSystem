package com.huangjie.sell.service.impl;

import com.huangjie.sell.converter.OrderMaster2OrderDTOConverter;
import com.huangjie.sell.dataobject.OrderDetail;
import com.huangjie.sell.dataobject.OrderMaster;
import com.huangjie.sell.dataobject.ProductInfo;
import com.huangjie.sell.dto.CardDTO;
import com.huangjie.sell.dto.OrderDTO;
import com.huangjie.sell.enums.OrderStatusEnum;
import com.huangjie.sell.enums.PayStatusEnum;
import com.huangjie.sell.enums.ResultEnum;
import com.huangjie.sell.exception.SellException;
import com.huangjie.sell.repository.OrderDetailRepository;
import com.huangjie.sell.repository.OrderMasterRepository;
import com.huangjie.sell.service.OrderMasterService;
import com.huangjie.sell.service.ProductInfoService;
import com.huangjie.sell.utils.KeyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by huangjie on 2019/7/21.
 */
@Service
@Slf4j
public class OrderMasterServiceImpl implements OrderMasterService {

    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;


    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        BigDecimal orderAccount = new BigDecimal(BigInteger.ZERO);
        String orderId = KeyUtils.genUniqueKey();

        //第一步 查询商品，价格
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {

            ProductInfo product = productInfoService.findOne(orderDetail.getProductId());
            if (product == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
            }
            //计算总价
            BigDecimal productPrice = product.getProductPrice();
            orderAccount = productPrice.multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAccount);

            //订单详情入库
            orderDetail.setDetailId(KeyUtils.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(product, orderDetail);
            orderDetailRepository.save(orderDetail);
        }


        //写入订单数据库（OrderMaster OrderDetail）
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setOrderAmount(orderAccount);
        orderMasterRepository.save(orderMaster);
        List<CardDTO> cardList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CardDTO(e.getProductId(), e.getProductQuantity())
                ).collect(Collectors.toList());
        //第四步扣库存
        productInfoService.decreaseStock(cardList);

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {

        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if (orderMaster == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIT);
        }
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIT);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> pageOrderMaster = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(pageOrderMaster.getContent());
        Page<OrderDTO> pageOrderDTO = new PageImpl<>(orderDTOList, pageable, pageOrderMaster.getTotalElements());
        return pageOrderDTO;
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        //首先判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【取消订单】 订单状态不正确 ,orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //修改订单状态
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        orderMasterRepository.save(orderMaster);
        //返还库存
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        if (CollectionUtils.isEmpty(orderDetailList)) {
            log.error("【返还库存】 订单详情为空 orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_EMPTY_ERROR);
        }
        List<CardDTO> cardDTOList = orderDetailList.stream().map(e -> new CardDTO(e.getProductId(),
                e.getProductQuantity())).collect(Collectors.toList());
        productInfoService.increaseStock(cardDTOList);
        //如果已经支付，需要退款

        if (orderDTO.getPayStatus().equals(PayStatusEnum.FINISH.getCode())) {
            //TODO 退款
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        if (!OrderStatusEnum.NEW.getCode().equals(orderDTO.getOrderStatus())) {
            log.error("【完结订单】订单状态错误 orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        orderDTO.setOrderStatus(OrderStatusEnum.FINISH.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【完结订单】 修改状态失败 orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        if (!OrderStatusEnum.NEW.getCode().equals(orderDTO.getOrderStatus())) {
            log.error("【支付订单】订单状态错误 orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //判断支付状态
        if (!PayStatusEnum.WAIT.getCode().equals(orderDTO.getPayStatus())) {
            log.error("【支付订单】订单支付状态错误 orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }

        //修改支付状态
        orderDTO.setPayStatus(PayStatusEnum.FINISH.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster save = orderMasterRepository.save(orderMaster);
        if (save == null) {
            log.error("【支付订单】 修改支付失败 orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        Page<OrderMaster> pageOrderMaster = orderMasterRepository.findAll(pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(pageOrderMaster.getContent());
        Page<OrderDTO> pageOrderDTO = new PageImpl<>(orderDTOList, pageable, pageOrderMaster.getTotalElements());
        return pageOrderDTO;
    }
}
