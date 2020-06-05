package com.mmall.user.controller;


import com.mmall.user.UserService;
import com.mmall.user.bean.User;
import com.mmall.user.vo.LoginVo;
import com.mmall.user.vo.RegisterVo;
import com.mmall.utils.JwtUtils;
import com.mmall.utils.R;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/****
 * @Author:qitianfeng
 * @Description:
 *****/
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("login")
    public R login(@RequestBody LoginVo user) {
        String token = userService.login(user);
        return R.ok().put("token", token);
    }

    @PostMapping("register")
    public R register(@RequestBody RegisterVo user) {
        userService.register(user);
        return R.ok();
    }

    @GetMapping("auth/getUserInfo")
    public R getUserInfo(HttpServletRequest request) {
        String memberIdByJwtToken = JwtUtils.getMemberIdByJwtToken(request);
        User loginVo = userService.getById(JwtUtils.getMemberIdByJwtToken(request));
        if (loginVo !=null) {

            LoginVo vo = new LoginVo();
            BeanUtils.copyProperties(loginVo,vo);
            return R.ok().put("loginInfo", vo);
        }
        return R.error().put("message","用户不存在");
    }
}
