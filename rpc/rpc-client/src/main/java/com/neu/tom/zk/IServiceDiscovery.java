package com.neu.tom.zk;

/**
 * 查找服务
 * Author: Wu Shuai
 * Date: 2018/7/21
 */
public interface IServiceDiscovery {
    /**
     * 根据请求的服务地址，获得对应的调用地址
     * @param serviceName
     * @return
     */
    String discover(String serviceName);
}
