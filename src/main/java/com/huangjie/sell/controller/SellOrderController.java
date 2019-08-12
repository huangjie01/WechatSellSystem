package com.huangjie.sell.controller;

import com.huangjie.sell.dto.OrderDTO;
import com.huangjie.sell.enums.ResultEnum;
import com.huangjie.sell.exception.SellException;
import com.huangjie.sell.service.OrderMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by huangjie on 2019/7/23.
 */
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellOrderController {
    @Autowired
    private OrderMasterService orderMasterService;

    /**
     * 卖家端订单列表
     *
     * @param page 从1开始
     * @param size
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") int page,
                             @RequestParam(value = "size", defaultValue = "10") int size,
                             Map<String, Object> map) {

        Pageable pageable = new PageRequest(page - 1, size);
        Page<OrderDTO> orderDTOPage = orderMasterService.findList(pageable);
        map.put("orderDTOPage", orderDTOPage);
        return new ModelAndView("order/list", map);
    }


    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId, Map<String, Object> map) {
        try {
            OrderDTO orderDTO = orderMasterService.findOne(orderId);
            orderMasterService.cancel(orderDTO);
        } catch (SellException e) {
            log.error("【卖家端取消订单】查询不到订单");
            map.put("msg", e.getMessage());
            map.put("url", "list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMsg());
        map.put("url", "list");
        return new ModelAndView("common/success");
    }

    public ModelAndView detail(@RequestParam("orderId") String orderId){

        return null;
    }
}
