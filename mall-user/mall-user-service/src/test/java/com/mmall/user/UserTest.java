package com.mmall.user;

import com.mmall.user.bean.User;
import com.mmall.user.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTest {
    @Resource
    private UserService userService;

    @Autowired
    UserMapper userMapper;
    @Test
    public void test(){

        User byId = userService.findById(123456L);
        System.err.println(byId);
    }
    @Test
    public void test1(){
        User user = new User();
        user.setUserName("111");
        user.setUserPassword("111");
        user.setMobile("1111");
        userMapper.insertSelective(user);
    }
}
