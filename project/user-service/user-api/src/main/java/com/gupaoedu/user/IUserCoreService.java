package com.gupaoedu.user;

import com.gupaoedu.user.dto.*;

/**
 * Author: Wu Shuai
 * Date: 2018/8/25
 */
public interface IUserCoreService {

    /**
     * 用户登录
     * @param request
     * @return
     */
    UserLoginRespose login(UserLoginRequest request);


    /**
     * 校验过程
     * @param request
     * @return
     */
    CheckAuthResponse validToken(CheckAuthRequest request);

    /**
     * 注册
     * @param userRegisterRequest
     * @return
     */
    UserRegisterResponse register(UserRegisterRequest userRegisterRequest);
}
