package com.huangjie.sell.controller;

import com.huangjie.sell.constant.CookieConstant;
import com.huangjie.sell.constant.RedisConstant;
import com.huangjie.sell.dataobject.SellerInfo;
import com.huangjie.sell.enums.ResultEnum;
import com.huangjie.sell.service.SellerInfoService;
import com.huangjie.sell.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openId") String openId, HttpServletResponse response, Map<String, Object> map) {
        //openId去和数据库的进行比对
        SellerInfo sellerInfo = sellerInfoService.findByOpenId(openId);
        if (sellerInfo == null) {
            map.put("msg", ResultEnum.LOGIN_FAIL.getMsg());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("/common/error", map);
        }
        //设置token至redis
        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token),
                openId, RedisConstant.EXPIRE_TIME, TimeUnit.SECONDS);
        //设置token至cookies
        CookieUtils.set(response, CookieConstant.TOKEN,token,CookieConstant.EXPIRE_TIME);
        return new ModelAndView("redirect:order/list");
    }

    @GetMapping("/loginOut")
    public ModelAndView loginOut( HttpServletRequest request,HttpServletResponse response,Map<String,Object> map){

        Cookie cookie = CookieUtils.get(request, CookieConstant.TOKEN);
        if (cookie!=null){
            //从redis中删除token
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));
            //清楚cookie
            CookieUtils.set(response,CookieConstant.TOKEN,null,0);
            map.put("msg",ResultEnum.LOGIN_OUT_SUCCESS.getMsg());
            map.put("url", "/sell/seller/order/list");
        }
        return new ModelAndView("/common/success", map);
    }

}
