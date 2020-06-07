package com.mmall.user.controller;


import com.mmall.user.UserService;
import com.mmall.user.bean.User;
import com.mmall.user.vo.LoginInfoVo;
import com.mmall.user.vo.LoginVo;
import com.mmall.user.vo.RegisterVo;
import com.mmall.utils.JwtUtils;
import com.mmall.utils.R;
import com.mmall.utils.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/****
 * @Author:qitianfeng
 * @Description:
 *****/
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("login")
    public Result login(@RequestBody LoginVo user) {
        String token = userService.login(user);
        return Result.ok().data("token",token);
    }

    @PostMapping("register")
    public Result register(@RequestBody RegisterVo user) {
        userService.register(user);
        return Result.ok();
    }

    @GetMapping("auth/getUserInfo")
    public Result getUserInfo(HttpServletRequest request) {
        String memberIdByJwtToken = JwtUtils.getMemberIdByJwtToken(request);
        User loginVo = userService.getById(JwtUtils.getMemberIdByJwtToken(request));
        if (loginVo !=null) {

            LoginInfoVo vo = new LoginInfoVo();
            BeanUtils.copyProperties(loginVo,vo);
            return Result.ok().data("loginInfo", vo);
        }
        return Result.error().message("用户不存在");
    }
}
