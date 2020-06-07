package com.mmall.cart.controller;

import com.mmall.cart.CartItemService;
import com.mmall.cart.bean.CartItem;
import com.mmall.utils.JwtUtils;
import com.mmall.utils.PageUtils;
import com.mmall.utils.R;
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
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = cartItemService.queryPage(params);

        return R.ok().put("message","查询成功");
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{cartId}")
    public R info(@PathVariable("cartId") Long cartId){
        CartItem cartItem = cartItemService.getById(cartId);

        return R.ok().put("message","查询成功");
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CartItem cartItem){
        cartItemService.save(cartItem);

        return R.ok().put("message","保存成功");
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CartItem cartItem){
        cartItemService.updateById(cartItem);

        return R.ok().put("message","更新成功");
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] cartIds){
        cartItemService.removeByIds(Arrays.asList(cartIds));

        return R.ok().put("message","查询成功");
    }
    //查询当前用户的购物车数据
    @GetMapping("/cartList")
    public R cartList(HttpServletRequest request){
//        Long userId = SecurityUtil.getUserId();
        String userId = JwtUtils.getMemberIdByJwtToken(request);
        List<CartItem> cartItemList = cartItemService.getCartByUserId(userId);
        return R.ok().put("message","查询成功");
    }
    //购物车删除
    @PostMapping("/cartDel")
    public R cartDel(@RequestBody Long goodId){
//        Long userId = SecurityUtil.getUserId();
//        Integer count =  cartItemService.deleteByGoodId(userId,goodId);
        return R.ok().put("message","查询成功");
    }
    //修改商品数量
    @PostMapping("cartEdit")
    public R cartEdit(@RequestBody Long goodId, @RequestBody BigDecimal goodNum, @RequestBody Integer checked,HttpServletRequest request){
        String userId = JwtUtils.getMemberIdByJwtToken(request);
        cartItemService.cartEdit(userId,goodId,goodNum,checked);
        return R.ok().put("message","修改成功");
    }

    /**
     * 是否全部选中
     */
    @PostMapping("editCheckAll")
    public R editCheckAll(@RequestBody Integer checkAll,HttpServletRequest request){
        String userId = JwtUtils.getMemberIdByJwtToken(request);
        cartItemService.editCheckAll(userId,checkAll);
        return R.ok().put("message","查询成功");
    }

    //加入到购物车
    @PostMapping("addCart")
    public R addCart(@RequestParam("goodId") String goodId,HttpServletRequest request){
        String userId = JwtUtils.getMemberIdByJwtToken(request);
        cartItemService.addCart(userId,goodId);
        return R.ok().put("message","加入购物车成功");
    }


}
