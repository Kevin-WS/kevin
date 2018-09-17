package com.kevin.tkt.user.dto;

import java.io.Serializable;

/**
 * 响应基本数据
 * @author: Wu Shuai
 * @create: 2018-09-16 20:22
 **/
public class ResponseBaseData implements Serializable {

    private static final long serialVersionUID = 8223322848390202220L;

    /**
     * 响应code
     */
    private String code;

    /**
     * 响应消息
     */
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
