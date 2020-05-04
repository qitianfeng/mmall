package com.mmall.cart;


import com.github.pagehelper.PageInfo;
import com.mmall.cart.bean.CartItem;

import java.util.List;

/****
 * @Author:qitianfeng
 * @Description:CartItem业务层接口
 *****/

public interface CartItemService {

    /***
     * CartItem多条件分页查询
     * @param cartItem
     * @param page
     * @param size
     * @return
     */
    PageInfo<CartItem> findPage(CartItem cartItem, int page, int size);

    /***
     * CartItem分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<CartItem> findPage(int page, int size);

    /***
     * CartItem多条件搜索方法
     * @param cartItem
     * @return
     */
    List<CartItem> findList(CartItem cartItem);

    /***
     * 删除CartItem
     * @param id
     */
    void delete(Long id);

    /***
     * 修改CartItem数据
     * @param cartItem
     */
    void update(CartItem cartItem);

    /***
     * 新增CartItem
     * @param cartItem
     */
    void add(CartItem cartItem);

    /**
     * 根据ID查询CartItem
     * @param id
     * @return
     */
     CartItem findById(Long id);

    /***
     * 查询所有CartItem
     * @return
     */
    List<CartItem> findAll();
}
