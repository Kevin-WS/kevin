package com.kevin.tkt.user;

import com.kevin.tkt.user.dto.UserLoginRequest;
import com.kevin.tkt.user.dto.UserLoginResponse;

/**
 * 用户核心服务
 * @author: Wu Shuai
 * @create: 2018-09-16 20:18
 **/
public interface IUserCoreService {

    /**
     * 用户登录
     * @param request
     * @return
     */
    UserLoginResponse login(UserLoginRequest request);
}
