package com.huangjie.sell.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huangjie
 * @date 2019/08/27
 * @blame 黄杰
 **/
public class CookieUtils {

    /**
     * 设置cookie
     *
     * @param response
     * @param name
     * @param value
     * @param maxAge
     */
    public static void set(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }


    /**
     * 获取cookie
     *
     * @param request
     * @param name
     * @return
     */
    public static Cookie get(HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = getCookieMap(request);
        if (cookieMap.containsKey(name)) {
            return cookieMap.get(name);
        }
        return null;
    }

    public static Map<String, Cookie> getCookieMap(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Map<String, Cookie> cookieMap = new HashMap<>();
        if (cookies!=null) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
}
