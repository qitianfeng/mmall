package com.mmall.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mmall.order.OrderService;
import com.mmall.order.entity.Order;
import com.mmall.utils.JwtUtils;
import com.mmall.utils.PageUtils;
import com.mmall.utils.R;
import com.mmall.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

/****
 * @Author:qitianfeng
 * @Description:
 *****/
@Api(value = "OrderController", description = "OrderController订单模块", tags = "OrderController")
@RequestMapping("/order")
@CrossOrigin
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;




    /**
     * 信息
     */
    @RequestMapping("/info/{orderId}")
    public Result info(@PathVariable("orderId") Long orderId, HttpServletRequest request) {

        String token = JwtUtils.getMemberIdByJwtToken(request);
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Order::getOrderId,orderId);
        wrapper.eq(Order::getUserId,token);
        Order serviceOne = orderService.getOne(wrapper);
        return Result.ok().data("order",serviceOne);
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody Long[] orderIds) {
        orderService.removeByIds(Arrays.asList(orderIds));

        return Result.ok();
    }

    @PostMapping("createOrder")
    public Result createOrder(@RequestBody Long[] goodsId, HttpServletRequest request){
        String token = JwtUtils.getMemberIdByJwtToken(request);
        orderService.createOrder(goodsId,token);
        return Result.ok();
    }







}
