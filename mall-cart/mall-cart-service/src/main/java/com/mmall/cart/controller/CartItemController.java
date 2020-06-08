package com.mmall.cart.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mmall.cart.CartItemService;
import com.mmall.cart.bean.CartItem;
import com.mmall.utils.JwtUtils;
import com.mmall.utils.PageUtils;
import com.mmall.utils.R;
import com.mmall.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("cart")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = cartItemService.queryPage(params);

        return Result.ok().data("cart",page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{cartId}")
    public Result info(@PathVariable("cartId") String cartId,HttpServletRequest request){
        String userId = JwtUtils.getMemberIdByJwtToken(request);
        LambdaQueryWrapper<CartItem> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CartItem::getUserId,userId);
        wrapper.eq(CartItem::getCartId,cartId);
        CartItem one = cartItemService.getOne(wrapper);
        return Result.ok().data("cartInfo",one);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public Result save(@RequestBody CartItem cartItem,HttpServletRequest request){
        cartItemService.save(cartItem);

        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody CartItem cartItem){

        cartItemService.updateById(cartItem);

        return Result.ok();
    }


    //查询当前用户的购物车数据
    @GetMapping("/cartList")
    public Result cartList(HttpServletRequest request){
        String userId = JwtUtils.getMemberIdByJwtToken(request);
        List<CartItem> cartItemList = cartItemService.getCartByUserId(userId);
        return Result.ok().data("cartList",cartItemList);
    }
    //购物车删除
    @PostMapping("/cartDel")
    public Result cartDel(@RequestBody Long goodId,HttpServletRequest request){
//        Long userId = SecurityUtil.getUserId();
        String userId = JwtUtils.getMemberIdByJwtToken(request);
        Boolean count =  cartItemService.deleteByGoodId(userId,goodId);
        return Result.ok();
    }
    //修改商品数量
    @PostMapping("cartEdit")
    public Result cartEdit(@RequestBody Long goodId, @RequestBody BigDecimal goodNum, @RequestBody Integer checked,HttpServletRequest request){
        String userId = JwtUtils.getMemberIdByJwtToken(request);
        cartItemService.cartEdit(userId,goodId,goodNum,checked);
        return Result.ok();
    }

    /**
     * 是否全部选中
     */
    @PostMapping("editCheckAll")
    public Result editCheckAll(@RequestBody Integer checkAll,HttpServletRequest request){
        String userId = JwtUtils.getMemberIdByJwtToken(request);
        cartItemService.editCheckAll(userId,checkAll);
        return Result.ok();
    }

    //加入到购物车
    @PostMapping("addCart")
    public Result addCart(@RequestParam("goodId") String goodId,HttpServletRequest request){
        String userId = JwtUtils.getMemberIdByJwtToken(request);
        cartItemService.addCart(userId,goodId);
        return Result.ok().message("加入购物车成功");
    }


}
