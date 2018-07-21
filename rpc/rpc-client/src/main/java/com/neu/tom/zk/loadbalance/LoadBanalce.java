package com.neu.tom.zk.loadbalance;

import java.util.List;

/**
 * 负载均衡接口
 * Author: Wu Shuai
 * Date: 2018/7/21
 */
public interface LoadBanalce {

    String selectHost(List<String> repos);
}


