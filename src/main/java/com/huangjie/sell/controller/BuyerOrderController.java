package com.huangjie.sell.controller;

import com.huangjie.sell.VO.ResultVO;
import com.huangjie.sell.converter.OrderForm2OrderDTOConverter;
import com.huangjie.sell.dto.OrderDTO;
import com.huangjie.sell.enums.ResultEnum;
import com.huangjie.sell.exception.SellException;
import com.huangjie.sell.form.OrderForm;
import com.huangjie.sell.service.OrderMasterService;
import com.huangjie.sell.utils.ResultVOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huangjie on 2019/7/22.
 */
@RestController
@RequestMapping("buyer/order")
@Slf4j
public class BuyerOrderController {
    @Autowired
    private OrderMasterService orderMasterService;

    /**
     * 创建订单
     * @param orderForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】 参数错误orderForm={}", orderForm);
            throw new SellException(bindingResult.getFieldError().getDefaultMessage(), ResultEnum.PARAM_ERROR.getCode());
        }
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】 购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO createResult = orderMasterService.create(orderDTO);
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("orderId", createResult.getOrderId());

        return ResultVOUtils.success(resultMap);
    }

    /**
     * 订单列表
     * @param openId
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openId") String openId,
                                         @RequestParam(value = "page", defaultValue = "0") int page,
                                         @RequestParam(value = "size", defaultValue = "10") int size) {
        if (StringUtils.isEmpty(openId)) {
            log.error("【查询订单列表】 openid 为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        Pageable pageable = new PageRequest(page, size);
        Page<OrderDTO> orderDTOPage = orderMasterService.findList(openId, pageable);
        return ResultVOUtils.success(orderDTOPage.getContent());
    }

    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openId") String openId, @RequestParam("orderId") String orderId) {
        //TODO 为了安全，需要验证这个人订单是否是这个人的
        OrderDTO orderDTO = orderMasterService.findOne(orderId);
        return ResultVOUtils.success(orderDTO);
    }

    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openId") String openId, @RequestParam("orderId") String orderId) {
        //TODO 为了安全，需要验证这个人订单是否是这个人的
        OrderDTO orderDTO = orderMasterService.findOne(orderId);
        if (orderDTO == null) {
            log.error("【取消订单】 订单不存在 orderId={}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIT);
        }
        orderMasterService.cancel(orderDTO);
        return ResultVOUtils.success(null);
    }
}
