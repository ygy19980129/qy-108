package com.aaa.ygy.controller;

import com.aaa.ygy.model.User;
import com.aaa.ygy.redis.RedisService;
import com.aaa.ygy.service.LoginService;
import com.aaa.ygy.vo.TokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: qy-108
 * @description:
 * @author: ygy
 * @create: 2020-05-15-2020/5/15 20:54
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private RedisService redisService;

    public TokenVo doLogin(@RequestBody User user){
        return loginService.doLongin(user,redisService);
    }

}
