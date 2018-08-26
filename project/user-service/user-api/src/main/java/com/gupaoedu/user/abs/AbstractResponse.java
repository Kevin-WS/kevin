package com.gupaoedu.user.abs;

import java.io.Serializable;

/**
 * Author: Wu Shuai
 * Date: 2018/8/25
 */
public class AbstractResponse implements Serializable {
    private static final long serialVersionUID = 7046382704161061715L;

    private String code;

    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
