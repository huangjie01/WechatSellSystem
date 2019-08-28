package com.huangjie.sell.aspect;

import com.huangjie.sell.constant.CookieConstant;
import com.huangjie.sell.constant.RedisConstant;
import com.huangjie.sell.exception.SellerAuthorizeException;
import com.huangjie.sell.utils.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
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

    @Autowired
    private StringRedisTemplate redisTemplate;

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
            throw new SellerAuthorizeException();
        }

        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX,
                cookie.getValue()));
        if (StringUtils.isEmpty(tokenValue)) {
            log.warn("【登录校验】查询不到token");
            throw new SellerAuthorizeException();
        }
    }


}
