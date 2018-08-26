package com.gupaoedu.sso.controller;

import com.gupaoedu.user.IUserCoreService;
import com.gupaoedu.user.dto.UserLoginRequest;
import com.gupaoedu.user.dto.UserLoginRespose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Author: Wu Shuai
 * Date: 2018/8/26
 */
@RestController
public class UserController extends BaseController {

    @Autowired
    IUserCoreService userCoreService;

    @PostMapping("/login")
    @Anoymous
    public ResponseEntity doLogin(String username, String password, HttpServletResponse response) {
        UserLoginRequest request = new UserLoginRequest();
        request.setUserName(username);
        request.setPassword(password);
        UserLoginRespose userLoginRespose = userCoreService.login(request);
        response.addHeader("Set-Cookie",
                "access_token=" + userLoginRespose.getToken() + ";Path=/;HttpOnly");
        return ResponseEntity.ok(userLoginRespose);
    }

    @GetMapping("/test")
    public String test() {
        return "success: " + getUid();
    }
}
