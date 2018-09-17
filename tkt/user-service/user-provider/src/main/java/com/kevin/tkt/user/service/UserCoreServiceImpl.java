package com.kevin.tkt.user.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.kevin.tkt.user.IUserCoreService;
import com.kevin.tkt.user.dto.ResponseBaseData;
import com.kevin.tkt.user.dto.UserLoginRequest;
import com.kevin.tkt.user.dto.UserLoginResponse;
import org.springframework.stereotype.Component;

/**
 * 用户核心服务Impl
 * @author: Wu Shuai
 * @create: 2018-09-16 20:27
 **/
@Component
@Service
public class UserCoreServiceImpl implements IUserCoreService {

    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        UserLoginResponse response = new UserLoginResponse();
        ResponseBaseData responseBaseData = new ResponseBaseData();
        responseBaseData.setCode("1");
        responseBaseData.setMsg("返回成功");
        response.setResponseBaseData(responseBaseData);
        return response;
    }
}
