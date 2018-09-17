package com.kevin.tkt.user.abs;

import com.kevin.tkt.user.dto.RequestBaseData;

import java.io.Serializable;

/**
 * 通用请求体
 * @author: Wu Shuai
 * @create: 2018-09-16 20:18
 **/
public abstract class AbstractRequest implements Serializable {

    private static final long serialVersionUID = -7558179662282308469L;
    /**
     * 请求基本数据
     */
    private RequestBaseData requestBaseData;

    public RequestBaseData getRequestBaseData() {
        return requestBaseData;
    }

    public void setRequestBaseData(RequestBaseData requestBaseData) {
        this.requestBaseData = requestBaseData;
    }
}
