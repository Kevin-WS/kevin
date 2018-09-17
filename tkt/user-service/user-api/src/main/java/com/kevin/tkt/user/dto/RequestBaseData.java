package com.kevin.tkt.user.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 请求基本数据
 * @author: Wu Shuai
 * @create: 2018-09-16 21:02
 **/
public class RequestBaseData implements Serializable {

    private static final long serialVersionUID = -3988958648511595550L;
    /**
     * 操作用户
     */
    private String dataOperator;

    /**
     * 请求扩展参数
     */
    private List<ExtensionParameter> extParameters;

    public String getDataOperator() {
        return dataOperator;
    }

    public void setDataOperator(String dataOperator) {
        this.dataOperator = dataOperator;
    }

    public List<ExtensionParameter> getExtParameters() {
        return extParameters;
    }

    public void setExtParameters(List<ExtensionParameter> extParameters) {
        this.extParameters = extParameters;
    }
}
