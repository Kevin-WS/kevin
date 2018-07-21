package com.neu.tom.zk;

/**
 * Author: Wu Shuai
 * Date: 2018/7/21
 */
public interface IRegisterCenter {
    /**
     * 注册服务名称和服务地址
     * @param serviceName
     * @param serviceAddress
     */
    void register(String serviceName,String serviceAddress) throws Exception;
}
