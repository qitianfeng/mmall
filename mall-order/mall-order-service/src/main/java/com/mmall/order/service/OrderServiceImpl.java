package com.mmall.order.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mmall.cart.CartItemService;
import com.mmall.cart.bean.CartItem;
import com.mmall.goods.GoodsService;
import com.mmall.goods.entity.Goods;
import com.mmall.order.OrderService;
import com.mmall.order.entity.Order;
import com.mmall.order.mapper.OrderMapper;
import com.mmall.user.UserService;
import com.mmall.user.bean.User;
import com.mmall.utils.IdWorker;
import com.mmall.utils.PageUtils;
import com.mmall.utils.Query;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/****
 * @Author:qitianfeng
 * @Description:Order业务层接口实现类
 *****/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Reference
    private GoodsService goodsService;

    @Reference
    private UserService userService;

    @Reference
    private CartItemService cartItemService;

    @Autowired
    private IdWorker idWorker;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<Order> page = this.page(
                new Query<Order>().getPage(params),
                new QueryWrapper<Order>()
        );

        return new PageUtils(page);
    }

    /**
     * 根据用户id创建订单
     *
     * @param goodsId
     * @param token
     */
    @Override
    public void createOrder(Long[] goodsId, String token) {

        for (Long id : goodsId) {

            Goods byId = goodsService.getById(id);

            Order order = new Order();

            order.setOrderId(String.valueOf(idWorker.nextId()));
            order.setGoodsPic(byId.getGoodsImage());
            order.setPayAmount(byId.getGoodsPrice());
            order.setUserId(token);
            User user = userService.getById(token);
            order.setUserName(user.getNickname());
            LambdaQueryWrapper<CartItem> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(CartItem::getGoodId,id);
            wrapper.eq(CartItem::getUserId,token);
            CartItem cartItem = cartItemService.getOne(wrapper);
            order.setTotalAmount(cartItem.getQuantity());
            order.setGoodsName(byId.getGoodsName());
            //计算总额
            order.setPayAmount(cartItem.getQuantity().multiply(byId.getGoodsPrice()));
            order.setCreateTime(new Date());

            //保存订单
            baseMapper.insert(order);
        }
    }

}
