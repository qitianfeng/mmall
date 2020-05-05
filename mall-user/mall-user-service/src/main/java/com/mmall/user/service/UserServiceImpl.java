package com.mmall.user.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mmall.user.UserService;
import com.mmall.user.bean.User;
import com.mmall.user.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/****
 * @Author:qitianfeng
 * @Description:User业务层接口实现类
 *****/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    /**
     * User条件+分页查询
     *
     * @param user 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<User> findPage(User user, int page, int size) {
        //分页
        PageHelper.startPage(page, size);
        //搜索条件构建
        Example example = createExample(user);
        //执行搜索
        return new PageInfo<User>(userMapper.selectByExample(example));
    }

    /**
     * User分页查询
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<User> findPage(int page, int size) {
        //静态分页
        PageHelper.startPage(page, size);
        //分页查询
        return new PageInfo<User>(userMapper.selectAll());
    }

    /**
     * User条件查询
     *
     * @param user
     * @return
     */
    @Override
    public List<User> findList(User user) {
        //构建查询条件
        Example example = createExample(user);
        //根据构建的条件查询数据
        return userMapper.selectByExample(example);
    }


    /**
     * User构建查询对象
     *
     * @param user
     * @return
     */
    public Example createExample(User user) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if (user != null) {
            // 用户ID
            if (user.getUserId() != null) {
                criteria.andEqualTo("userId", user.getUserId());
            }
            // 用户名
            if (!StringUtils.isEmpty(user.getUserName())) {
                criteria.andEqualTo("userName", user.getUserName());
            }
            // 用户密码
            if (!StringUtils.isEmpty(user.getUserPassword())) {
                criteria.andEqualTo("userPassword", user.getUserPassword());
            }
            // 用户积分
            if (!StringUtils.isEmpty(user.getUserScore())) {
                criteria.andEqualTo("userScore", user.getUserScore());
            }
            // 用户手机
            if (!StringUtils.isEmpty(user.getUserMobile())) {
                criteria.andEqualTo("userMobile", user.getUserMobile());
            }
            // 用户余额
            if (!StringUtils.isEmpty(user.getUserMoney())) {
                criteria.andEqualTo("userMoney", user.getUserMoney());
            }
            // 用户注册时间
            if (user.getUserRegTime() != null) {
                criteria.andEqualTo("userRegTime", user.getUserRegTime());
            }
            // 盐
            if (!StringUtils.isEmpty(user.getSalt())) {
                criteria.andEqualTo("salt", user.getSalt());
            }
        }
        return example;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        userMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改User
     *
     * @param user
     */
    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    /**
     * 增加User
     *
     * @param user
     */
    @Override
    public void add(User user) {
        userMapper.insert(user);
    }

    /**
     * 根据ID查询User
     * @param id
     * @return
     */
    @Override
    public User findById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询User全部数据
     *
     * @return
     */
    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }
}
