package com.neu.tom;

import com.neu.tom.anno.RpcAnnotation;

/**
 * Author: Wu Shuai
 * Date: 2018/7/21
 */
@RpcAnnotation(value = HelloService.class, version = "2.0")
public class HelloServiceImpl2 implements HelloService {

    @Override
    public String sayHello(String message) {
        return "Hello 2.0, " + message;
    }
}
