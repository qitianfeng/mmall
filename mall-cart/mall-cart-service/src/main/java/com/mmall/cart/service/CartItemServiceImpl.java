package com.mmall.cart.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mmall.cart.CartItemService;
import com.mmall.cart.bean.CartItem;
import com.mmall.cart.mapper.CartItemMapper;
import com.mmall.goods.GoodsService;
import com.mmall.goods.entity.Goods;
import com.mmall.utils.PageUtils;
import com.mmall.utils.Query;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/****
 * @Author:qitianfeng
 * @Description:CartItem业务层接口实现类
 *****/
@Service
@Slf4j
public class CartItemServiceImpl extends ServiceImpl<CartItemMapper, CartItem> implements CartItemService {

    @Autowired
    private CartItemMapper cartItemMapper;

    @Reference
    private GoodsService goodsService;


    /**
     * 根据商品id删除
     *
     * @param goodId
     * @return
     */
    @Override
    public Boolean deleteByGoodId(Long userId, Long goodId) {

        LambdaQueryWrapper<CartItem> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CartItem::getUserId, userId);
        wrapper.eq(CartItem::getGoodId, goodId);
        boolean remove = this.remove(wrapper);
        return remove;
    }


    /**
     * 更新购物车为全部选中 或取消全选
     *
     * @param userId
     * @param checkAll
     */
    @Override
    public void editCheckAll(String userId, Integer checkAll) {

        CartItem cartItem = new CartItem();
        //todo
        cartItem.setUserId(userId);

        cartItem.setCheckStatus(checkAll);

        ///更新
        cartItemMapper.updateById(cartItem);
    }

    /**
     * 将商品加入到购物车
     *
     * @param userId
     */
    @Override
    public void addCart(String userId, String goodId) {
        CartItem cartItem = new CartItem();
        cartItem.setUserId(userId);
        cartItem.setGoodId(goodId);
        LambdaQueryWrapper<CartItem> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CartItem::getGoodId, goodId);
        wrapper.eq(CartItem::getUserId, userId);
        //查询数据看是否存在该商品，如果有存在，则数量加一
        CartItem item = this.getOne(wrapper);
        if (item != null) {
            item.setQuantity(item.getQuantity().add( (new BigDecimal(1))));
        }

        //远程调用goods服务
        Goods goods = goodsService.getById(goodId);

        cartItem.setQuantity(new BigDecimal(1));
        cartItem.setCreateDate(new Date());
        cartItem.setCheckStatus(1);
        cartItem.setGoodName(goods.getGoodsName());
        cartItem.setGoodPic(goods.getGoodsImage());
        cartItem.setModifyDate(new Date());
        cartItem.setGoodId(goodId);
        cartItemMapper.insert(cartItem);


    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String pageIndex = (String) params.get("page");
        String limit = (String) params.get("limit");
        LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();

        IPage<CartItem> page = this.page(
                new Query<CartItem>().getPage(params),
                new QueryWrapper<CartItem>()
        );
        return new PageUtils(page);
    }

    /**
     * 返回用户的购物车
     *
     * @param userId
     * @return
     */
    @Override
    public List<CartItem> getCartByUserId(String userId) {
        LambdaQueryWrapper<CartItem> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(CartItem::getCreateDate);
        wrapper.eq(CartItem::getUserId, userId);
        List<CartItem> list = this.list(wrapper);
        return list;
    }

    /**
     * 更新购物车里单个商品的数量
     *
     * @param userId
     * @param goodId
     * @param goodNum
     * @param checked
     */
    @Override
    public void cartEdit(String userId, Long goodId, BigDecimal goodNum, Integer checked) {
        LambdaQueryWrapper<CartItem> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CartItem::getUserId,userId);
        wrapper.eq(CartItem::getGoodId,goodId);
        CartItem cartItem = baseMapper.selectOne(wrapper);
        Integer check = checked == 1 ? 1 : 0;
        cartItem.setQuantity(goodNum);
        cartItem.setCheckStatus(check);
        baseMapper.updateById(cartItem);
    }
}
