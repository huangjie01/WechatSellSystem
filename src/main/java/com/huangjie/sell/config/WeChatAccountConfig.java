package com.huangjie.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author huangjie
 * @date 2019/08/20
 * @blame 黄杰
 **/
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WeChatAccountConfig {
    private String mpAppId;
    private String mpAppSecret;
    private String openAppId;
    private String openAppSecret;
    private String mchId;
    private String mchKey;
    private String keyPath;
    private String notifyUrl;


}
