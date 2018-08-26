package com.gupaoedu.user.dto;

import com.gupaoedu.user.abs.AbstractResponse;

/**
 * @description: 校验Token Response
 * @author: Wu Shuai
 * @create: 2018-08-26 11:59
 **/
public class CheckAuthResponse extends AbstractResponse {

    private static final long serialVersionUID = -1732359932043657073L;

    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "CheckAuthResponse{" +
                "uid='" + uid + '\'' +
                "} " + super.toString();
    }
}
