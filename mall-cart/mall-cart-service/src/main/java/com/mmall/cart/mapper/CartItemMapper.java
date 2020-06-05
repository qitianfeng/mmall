package com.mmall.cart.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mmall.cart.bean.CartItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartItemMapper extends BaseMapper<CartItem> {

}
