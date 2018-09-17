package com.kevin.tkt.sso.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kevin.tkt.user.IUserCoreService;
import com.kevin.tkt.user.dto.UserLoginRequest;
import com.kevin.tkt.user.dto.UserLoginResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * 用户控制器
 * @author: Wu Shuai
 * @create: 2018-09-16 21:44
 **/
@RestController
public class UserController {

    @Reference
    private IUserCoreService userCoreService;

    @RequestMapping("/login")
    public String login(String username, String password) {
        UserLoginRequest request = new UserLoginRequest();
        request.setUsername(username);
        request.setPassword(password);
        UserLoginResponse response = userCoreService.login(request);
        return Optional.ofNullable(response).map(x -> x.getResponseBaseData()).map(x -> x.getMsg()).orElse("登录失败");
    }
}
