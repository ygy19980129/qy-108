package com.aaa.ygy.controller;

import com.aaa.ygy.base.BaseController;
import com.aaa.ygy.base.ResultData;
import com.aaa.ygy.model.User;
import com.aaa.ygy.service.IQYService;
import com.aaa.ygy.vo.TokenVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: qy-108
 * @description:
 * @author: ygy
 * @create: 2020-05-15-2020/5/15 18:57
 */
@RestController
@Api(value = "登录信息",tags = "用户登录接口")
public class LoginController extends BaseController {

    @Autowired
    private IQYService qyService;


    /**
     * @Description: 执行登录操作
     * @Param: [user]
     * @return: com.aaa.ygy.base.ResultData
     * @Author: ygy
     * @Date: 2020/5/15 21:05
     */
    @PostMapping("/doLogin")
    @ApiOperation(value = "登录功能", notes = "用户执行登录功能")
    public ResultData doLogin(User user) {
        TokenVo tokenVo = qyService.doLogin(user);
        if(tokenVo.getIfSuccess()) {
            return super.loginSuccess(tokenVo.getToken());
        }
        return super.loginFailed();
    }
}
