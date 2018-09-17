package com.kevin.tkt.user.abs;

import com.kevin.tkt.user.dto.ResponseBaseData;

import java.io.Serializable;

/**
 * 通用响应体
 * @author: Wu Shuai
 * @create: 2018-09-16 20:22
 **/
public abstract class AbstractResponse implements Serializable {

    private static final long serialVersionUID = 1382423877416986068L;

    /**
     * 响应基本数据
     */
    private ResponseBaseData responseBaseData;

    public ResponseBaseData getResponseBaseData() {
        return responseBaseData;
    }

    public void setResponseBaseData(ResponseBaseData responseBaseData) {
        this.responseBaseData = responseBaseData;
    }
}
