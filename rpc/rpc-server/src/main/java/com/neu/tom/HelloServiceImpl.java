package com.neu.tom;

import com.neu.tom.anno.RpcAnnotation;

/**
 * Author: Wu Shuai
 * Date: 2018/7/21
 */
@RpcAnnotation(HelloService.class)
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String message) {
        return "Hello, " + message;
    }
}
