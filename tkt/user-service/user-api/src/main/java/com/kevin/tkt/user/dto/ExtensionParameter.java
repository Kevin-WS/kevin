package com.kevin.tkt.user.dto;

import java.io.Serializable;

/**
 * 扩展参数
 * @author: Wu Shuai
 * @create: 2018-09-16 20:21
 **/
public class ExtensionParameter implements Serializable {

    private static final long serialVersionUID = -2433235629604519828L;

    /**
     * key
     */
    private String key;

    /**
     * value
     */
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ExtensionParameter{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
