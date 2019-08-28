package com.huangjie.sell;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.huangjie.sell.dataobject.mapper")
public class WechatSellApplication {
	public static void main(String[] args) {
		SpringApplication.run(WechatSellApplication.class, args);
	}

}
