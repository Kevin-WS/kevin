package com.kevin.tkt.user.dto;

import com.kevin.tkt.user.abs.AbstractRequest;

/**
 * @author: Wu Shuai
 * @create: 2018-09-16 20:23
 **/
public class UserLoginRequest extends AbstractRequest {

    private static final long serialVersionUID = -4106621659356732704L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return super.toString() + "UserLoginRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
