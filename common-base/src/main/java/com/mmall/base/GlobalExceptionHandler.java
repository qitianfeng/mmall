package com.mmall.base;


import com.mmall.utils.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 */
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.error();
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e){
        e.printStackTrace();
        return R.error().put("message","执行了自定义异常");
    }

    @ExceptionHandler(QiException.class)
    @ResponseBody
    public R error(QiException e){
        e.printStackTrace();
        return R.ok().error().put("message",e.getMsg());
    }
}
