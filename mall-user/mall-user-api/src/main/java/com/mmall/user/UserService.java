package com.mmall.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mmall.user.bean.User;
import com.mmall.user.vo.LoginVo;
import com.mmall.user.vo.RegisterVo;
import com.mmall.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-03 15:58:49
 */
public interface UserService extends IService<User> {

    PageUtils queryPage(Map<String, Object> params);


    /**
     * 登录
     * @param user
     * @return
     */
    String login(LoginVo user);

    /**
     * 注册
     * @param user
     */
    void register(RegisterVo user);

    /**
     * 获取用户信息
     * @return
     */
    LoginVo getLoginInfo();

}
