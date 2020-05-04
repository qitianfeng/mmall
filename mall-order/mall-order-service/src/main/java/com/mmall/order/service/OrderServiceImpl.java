package com.mmall.order.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mmall.order.OrderService;
import com.mmall.order.bean.Order;
import com.mmall.order.mapper.OrderMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/****
 * @Author:qitianfeng
 * @Description:Order业务层接口实现类
 *****/
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;


    /**
     * Order条件+分页查询
     * @param order 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Order> findPage(Order order, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(order);
        //执行搜索
        return new PageInfo<Order>(orderMapper.selectByExample(example));
    }

    /**
     * Order分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Order> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Order>(orderMapper.selectAll());
    }

    /**
     * Order条件查询
     * @param order
     * @return
     */
    @Override
    public List<Order> findList(Order order){
        //构建查询条件
        Example example = createExample(order);
        //根据构建的条件查询数据
        return orderMapper.selectByExample(example);
    }


    /**
     * Order构建查询对象
     * @param order
     * @return
     */
    public Example createExample(Order order){
        Example example=new Example(Order.class);
        Example.Criteria criteria = example.createCriteria();
        if(order!=null){
            // 订单ID
            if(!StringUtils.isEmpty(order.getOrderId())){
                    criteria.andEqualTo("orderId",order.getOrderId());
            }
            // 用户ID
            if(!StringUtils.isEmpty(order.getUserId())){
                    criteria.andEqualTo("userId",order.getUserId());
            }
            // 创建时间
            if(!StringUtils.isEmpty(order.getCreateTime())){
                    criteria.andEqualTo("createTime",order.getCreateTime());
            }
            // 用户名
            if(!StringUtils.isEmpty(order.getUserName())){
                    criteria.andEqualTo("userName",order.getUserName());
            }
            // 订单总金额
            if(!StringUtils.isEmpty(order.getTotalAmount())){
                    criteria.andEqualTo("totalAmount",order.getTotalAmount());
            }
            // 应付金额（实际支付金额）
            if(!StringUtils.isEmpty(order.getPayAmount())){
                    criteria.andEqualTo("payAmount",order.getPayAmount());
            }
            // 支付方式：0->未支付；1->支付宝；
            if(!StringUtils.isEmpty(order.getPayType())){
                    criteria.andEqualTo("payType",order.getPayType());
            }
            // 用户余额
            if(!StringUtils.isEmpty(order.getMoneyAmount())){
                    criteria.andEqualTo("moneyAmount",order.getMoneyAmount());
            }
            // 支付时间
            if(!StringUtils.isEmpty(order.getPaymentTime())){
                    criteria.andEqualTo("paymentTime",order.getPaymentTime());
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
        orderMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Order
     * @param order
     */
    @Override
    public void update(Order order){
        orderMapper.updateByPrimaryKey(order);
    }

    /**
     * 增加Order
     * @param order
     */
    @Override
    public void add(Order order){
        orderMapper.insert(order);
    }

    /**
     * 根据ID查询Order
     * @param id
     * @return
     */
    @Override
    public Order findById(Long id){
        return  orderMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Order全部数据
     * @return
     */
    @Override
    public List<Order> findAll() {
        return orderMapper.selectAll();
    }
}
