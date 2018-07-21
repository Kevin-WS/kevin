package com.neu.tom;

import com.google.common.collect.Maps;
import com.neu.tom.anno.RpcAnnotation;
import com.neu.tom.zk.IRegisterCenter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author: Wu Shuai
 * Date: 2018/7/21
 */
public class RpcServer {
    /**
     * 创建一个线程池
     */
    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    /**
     * 注册中心
     */
    private IRegisterCenter registerCenter;

    /**
     * 服务地址
     */
    private String serviceAddress;

    /**
     * 存放服务名称和服务对象之间的关系
     */
    private Map<String, Object> handlerMap;

    public RpcServer(IRegisterCenter registerCenter, String serviceAddress) {
        this.registerCenter = registerCenter;
        this.serviceAddress = serviceAddress;

        handlerMap = Maps.newHashMap();
    }

    /**
     * 绑定服务
     * @param services
     */
    public void bind(Object... services){
        for(Object service : services){
            RpcAnnotation annotation = service.getClass().getAnnotation(RpcAnnotation.class);
            String serviceName = annotation.value().getName();
            String version = annotation.version();
            if (version != null && !version.equals("")){
                serviceName = serviceName + "-" + version;
            }
            //绑定服务接口名称对应的服务
            handlerMap.put(serviceName,service);
            System.out.println("绑定服务成功" + serviceName);
        }
    }

    /**
     * 发布服务
     */
    public void publisher() {
        ServerSocket serverSocket = null;
        try {
            String[] addressArr = serviceAddress.split(":");
            serverSocket = new ServerSocket(Integer.parseInt(addressArr[1]));  //启动一个服务监听
            for (String serviceName : handlerMap.keySet()) {
                registerCenter.register(serviceName, serviceAddress);
                System.out.println("注册服务成功：" + serviceName + "->" + serviceAddress);
            }

            // 循环监听
            while (true) {
                Socket socket = serverSocket.accept();
                //通过线程池去处理请求
                executorService.execute(new ProcessorHandler(socket, handlerMap));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
