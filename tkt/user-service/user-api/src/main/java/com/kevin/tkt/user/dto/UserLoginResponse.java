package com.kevin.tkt.user.dto;

import com.kevin.tkt.user.abs.AbstractResponse;

/**
 * @author: Wu Shuai
 * @create: 2018-09-16 20:25
 **/
public class UserLoginResponse extends AbstractResponse {

    private static final long serialVersionUID = -2368928752995882699L;

    /**
     * 用户ID
     */
    private Long uid;

    /**
     * 登录成功token
     */
    private String token;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
