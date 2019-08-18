package com.huangjie.sell.controller;

import com.huangjie.sell.dataobject.ProductInfo;
import com.huangjie.sell.service.ProductInfoService;
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
 * ${DESCRIPTION}
 *
 * @author huangjie
 * @version 2019/8/15 22:44
 * @blame 黄杰
 */
@Controller
@RequestMapping("/seller/product")
public class SellerProductContainer {
    @Autowired
   private ProductInfoService productInfoService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue ="1") int page,
                             @RequestParam(value = "size",defaultValue = "5") int size,
                             Map<String,Object> map){
        Pageable pageable=new PageRequest(page-1,size);
        Page<ProductInfo> productInfoPage = productInfoService.findAll(pageable);
        map.put("productInfoPage",productInfoPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("product/list",map);
    }
}
