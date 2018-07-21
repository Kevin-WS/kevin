package com.neu.tom;

import com.neu.tom.zk.IRegisterCenter;
import com.neu.tom.zk.RegisterCenterImpl;

/**
 * 服务端demo
 * Author: Wu Shuai
 * Date: 2018/7/21
 */
public class ServerDemo {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        HelloService helloService2 = new HelloServiceImpl2();
        IRegisterCenter registerCenter = new RegisterCenterImpl();
        RpcServer rpcServer = new RpcServer(registerCenter, "127.0.0.1:8080");
        rpcServer.bind(helloService, helloService2);
        rpcServer.publisher();
    }
}
