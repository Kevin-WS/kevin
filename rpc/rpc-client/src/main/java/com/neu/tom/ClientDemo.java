package com.neu.tom;

import com.neu.tom.zk.IServiceDiscovery;
import com.neu.tom.zk.ServiceDiscoveryImpl;

/**
 * 客户端demo
 * Author: Wu Shuai
 * Date: 2018/7/21
 */
public class ClientDemo {

    public static void main(String[] args) throws InterruptedException {
        IServiceDiscovery serviceDiscovery = new ServiceDiscoveryImpl();
        RpcClientProxy rpcClientProxy = new RpcClientProxy(serviceDiscovery);
        HelloService hello = rpcClientProxy.clientProxy(HelloService.class, "2.0");
        System.out.println(hello.sayHello("test"));
    }
}
