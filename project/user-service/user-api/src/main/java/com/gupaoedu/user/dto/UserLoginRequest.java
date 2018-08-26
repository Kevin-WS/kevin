package com.gupaoedu.user.dto;

import com.gupaoedu.user.abs.AbstractRequest;

/**
 * Author: Wu Shuai
 * Date: 2018/8/25
 */
public class UserLoginRequest extends AbstractRequest {
    private static final long serialVersionUID = -5249710782115573477L;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
