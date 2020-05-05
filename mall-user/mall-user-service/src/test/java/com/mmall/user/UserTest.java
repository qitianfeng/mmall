package com.mmall.user;

import com.mmall.user.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTest {
    @Resource
    private UserService userService;

    @Test
    public void test(){
        User byId = userService.findById(1L);
        System.err.println(byId);
    }
}
