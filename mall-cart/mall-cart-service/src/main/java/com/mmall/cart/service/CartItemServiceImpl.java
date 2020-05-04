package com.mmall.cart.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mmall.cart.CartItemService;
import com.mmall.cart.bean.CartItem;
import com.mmall.cart.mapper.CartItemMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/****
 * @Author:qitianfeng
 * @Description:CartItem业务层接口实现类
 *****/
@Service
@Slf4j
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemMapper cartItemMapper;


    /**
     * CartItem条件+分页查询
     * @param cartItem 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<CartItem> findPage(CartItem cartItem, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(cartItem);
        //执行搜索
        return new PageInfo<CartItem>(cartItemMapper.selectByExample(example));
    }

    /**
     * CartItem分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<CartItem> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<CartItem>(cartItemMapper.selectAll());
    }

    /**
     * CartItem条件查询
     * @param cartItem
     * @return
     */
    @Override
    public List<CartItem> findList(CartItem cartItem){
        //构建查询条件
        Example example = createExample(cartItem);
        //根据构建的条件查询数据
        return cartItemMapper.selectByExample(example);
    }


    /**
     * CartItem构建查询对象
     * @param cartItem
     * @return
     */
    public Example createExample(CartItem cartItem){
        Example example=new Example(CartItem.class);
        Example.Criteria criteria = example.createCriteria();
        if(cartItem!=null){
            // 购物车ID
            if(!StringUtils.isEmpty(cartItem.getCartId())){
                    criteria.andEqualTo("cartId",cartItem.getCartId());
            }
            // 商品ID
            if(!StringUtils.isEmpty(cartItem.getGoodId())){
                    criteria.andEqualTo("goodId",cartItem.getGoodId());
            }
            // 用户ID
            if(!StringUtils.isEmpty(cartItem.getUserId())){
                    criteria.andEqualTo("userId",cartItem.getUserId());
            }
            // 购买数量
            if(!StringUtils.isEmpty(cartItem.getQuantity())){
                    criteria.andEqualTo("quantity",cartItem.getQuantity());
            }
            // 商品主图
            if(!StringUtils.isEmpty(cartItem.getGoodPic())){
                    criteria.andEqualTo("goodPic",cartItem.getGoodPic());
            }
            // 商品名字
            if(!StringUtils.isEmpty(cartItem.getGoodName())){
                    criteria.andEqualTo("goodName",cartItem.getGoodName());
            }
            // 创建时间
            if(!StringUtils.isEmpty(cartItem.getCreateDate())){
                    criteria.andEqualTo("createDate",cartItem.getCreateDate());
            }
            // 修改时间
            if(!StringUtils.isEmpty(cartItem.getModifyDate())){
                    criteria.andEqualTo("modifyDate",cartItem.getModifyDate());
            }
            // 删除状态
            if(!StringUtils.isEmpty(cartItem.getDeleteStatus())){
                    criteria.andEqualTo("deleteStatus",cartItem.getDeleteStatus());
            }
        }
        return example;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Long id){
        cartItemMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改CartItem
     * @param cartItem
     */
    @Override
    public void update(CartItem cartItem){
        cartItemMapper.updateByPrimaryKey(cartItem);
    }

    /**
     * 增加CartItem
     * @param cartItem
     */
    @Override
    public void add(CartItem cartItem){
        cartItemMapper.insert(cartItem);
    }

    /**
     * 根据ID查询CartItem
     * @param id
     * @return
     */
    @Override
    public CartItem findById(Long id){
        return  cartItemMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询CartItem全部数据
     * @return
     */
    @Override
    public List<CartItem> findAll() {
        return cartItemMapper.selectAll();
    }
}
