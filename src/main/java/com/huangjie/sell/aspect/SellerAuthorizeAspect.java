package com.huangjie.sell.aspect;

import com.huangjie.sell.constant.CookieConstant;
import com.huangjie.sell.utils.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author huangjie
 * @date 2019/08/27
 * @blame 黄杰
 **/
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {
    /**
     * 验证
     */
    @Pointcut("execution(public * com.huangjie.sell.controller.Seller*.*(..))" +
            "&& !execution(public  * com.huangjie.sell.controller.SellerUserController.*(..))")
    public void verify() {
    }

    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        //查询cookie
        Cookie cookie = CookieUtils.get(request, CookieConstant.TOKEN);
        if (cookie == null) {
               log.error("【登录校验】 cookie中查询不到token");
        }
    }


}
