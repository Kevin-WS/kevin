package com.gupaoedu.user.dto;

import com.gupaoedu.user.abs.AbstractRequest;

/**
 * @description: 校验Tocken Request
 * @author: Wu Shuai
 * @create: 2018-08-26 11:58
 **/
public class CheckAuthRequest extends AbstractRequest {
    private static final long serialVersionUID = -7254784570429150625L;

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "CheckAuthRequest{" +
                "token='" + token + '\'' +
                '}';
    }
}
