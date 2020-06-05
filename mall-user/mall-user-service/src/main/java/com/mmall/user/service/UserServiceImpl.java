package com.mmall.user.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mmall.base.QiException;
import com.mmall.user.UserService;
import com.mmall.user.bean.User;
import com.mmall.user.mapper.UserMapper;
import com.mmall.user.vo.LoginVo;
import com.mmall.user.vo.RegisterVo;
import com.mmall.utils.JwtUtils;
import com.mmall.utils.MD5;
import com.mmall.utils.PageUtils;
import com.mmall.utils.Query;
import org.apache.dubbo.config.annotation.Service;

import java.util.List;
import java.util.Map;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<User> page = this.page(
                new Query<User>().getPage(params),
                new QueryWrapper<User>()
        );

        return new PageUtils(page);
    }

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @Override
    public String login(LoginVo user) {

        String userName = user.getUserName();
        String password = user.getUserPassword();

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName,userName);
        User user1 = baseMapper.selectOne(wrapper);

        if (user1 == null) {
            throw new QiException(20001,"用户不存在");
        }
        String token = JwtUtils.getJwtToken(user1.getUserId(), userName);

        return token;
    }

    /**
     * 注册
     *
     * @param user
     */
    @Override
    public void register(RegisterVo user) {

        String mobile = user.getUserMobile();
        String userName = user.getUserName();
        String password = user.getUserPassword();

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserMobile, mobile);
        User selectOne = baseMapper.selectOne(wrapper);
        if (selectOne != null) {
            throw new QiException(20001,"手机号已被注册");
        }

        String encrypt = MD5.encrypt(password);

        User user1 = new User();
        user1.setUserName(userName);
        user1.setUserMobile(mobile);
        user1.setUserPassword(password);
        baseMapper.insert(user1);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @Override
    public LoginVo getLoginInfo() {
        return null;
    }

}
