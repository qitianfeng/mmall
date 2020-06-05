package com.mmall.cart;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mmall.cart.bean.CartItem;
import com.mmall.utils.PageUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CartItemService extends IService<CartItem> {

    Boolean deleteByGoodId(Long userId, Long goodId);

    void editCheckAll(String userId, Integer checkAll);

    void addCart(String userId, String goodId);

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 返回用户的购物车
     * @param userId
     * @return
     */
    List<CartItem> getCartByUserId(String userId);

    /**
     * 更新购物车里单个商品的数量
     * @param userId
     * @param goodId
     * @param goodNum
     * @param checked
     */
    void cartEdit(String userId, Long goodId, BigDecimal goodNum, Integer checked);
}

