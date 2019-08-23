package com.huangjie.sell.controller;

import com.huangjie.sell.dataobject.SellerInfo;
import com.huangjie.sell.enums.ResultEnum;
import com.huangjie.sell.service.SellerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author huangjie
 * @date 2019/08/20
 * @blame 黄杰
 **/
@Controller
@RequestMapping("/seller")
public class SellerUserController {
    @Autowired
  private SellerInfoService sellerInfoService;
    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openId") String openId, Map<String,Object> map){
        //openId去和数据库的进行比对
        SellerInfo sellerInfo = sellerInfoService.findByOpenId(openId);
        if (sellerInfo==null){
            map.put("msg", ResultEnum.LOGIN_FAIL.getMsg());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("/common/error",map);
        }
        //设置token至redis
        //设置token至cookies
        return null;
    }
}
