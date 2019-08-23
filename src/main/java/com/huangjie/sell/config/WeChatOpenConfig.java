/*
package com.huangjie.sell.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

*/
/**
 * @author huangjie
 * @date 2019/08/20
 * @blame 黄杰
 **//*

public class WeChatOpenConfig {
    @Autowired
private WeChatAccountConfig weChatAccountConfig;

    @Bean
    public WxMpService wxOpenService(){
        WxMpService wxMpService=new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxOpenConfigStorage());
        return wxMpService;
    }

    @Bean
    public WxMpConfigStorage wxOpenConfigStorage(){
        WxMpInMemoryConfigStorage wxMpConfigStorage=new WxMpInMemoryConfigStorage();
        wxMpConfigStorage.setAppId(weChatAccountConfig.getOpenAppId());
        wxMpConfigStorage.setSecret(weChatAccountConfig.getOpenAppSecret());
        return wxMpConfigStorage;

    }
}
*/
