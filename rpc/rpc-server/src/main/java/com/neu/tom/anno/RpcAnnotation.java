package com.neu.tom.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author: Wu Shuai
 * Date: 2018/7/21
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RpcAnnotation {

    /**
     * 对外发布的服务的接口名称
     * @return
     */
    Class<?> value();

    /**
     * 版本号
     * @return
     */
    String version() default "";
}
