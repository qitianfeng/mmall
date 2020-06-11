package com.mmall.order;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mmall.order.entity.Order;
import com.mmall.utils.PageUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/****
 * @Author:qitianfeng
 * @Description:Order业务层接口
 *****/
public interface OrderService extends IService<Order> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 根据用户id创建订单
     * @param goodsId
     * @param token
     */
    void createOrder(Long[] goodsId, String token);
}
