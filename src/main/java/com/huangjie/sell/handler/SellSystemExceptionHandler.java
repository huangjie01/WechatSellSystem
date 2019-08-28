package com.huangjie.sell.handler;

import com.huangjie.sell.VO.ResultVO;
import com.huangjie.sell.exception.SellException;
import com.huangjie.sell.exception.SellerAuthorizeException;
import com.huangjie.sell.utils.ResultVOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author huangjie
 * @date 2019/08/28
 * @blame 黄杰
 **/
@ControllerAdvice
public class SellSystemExceptionHandler {

    @ExceptionHandler(value = SellerAuthorizeException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView handlerAuthorizeException(){
        return new ModelAndView("/login/login");
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellException(SellException e){
         return ResultVOUtils.error(e.getCode(),e.getMessage());
    }
}
