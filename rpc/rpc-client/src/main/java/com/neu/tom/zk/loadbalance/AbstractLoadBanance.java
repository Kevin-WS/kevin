package com.neu.tom.zk.loadbalance;

import java.util.List;

/**
 * 负载均衡模板
 * Author: Wu Shuai
 * Date: 2018/7/21
 */
public abstract class AbstractLoadBanance implements LoadBanalce {

    @Override
    public String selectHost(List<String> repos) {
        if (repos == null || repos.size() == 0) {
            return null;
        }
        if (repos.size() == 1) {
            return repos.get(0);
        }
        return doSelect(repos);
    }

    protected abstract String doSelect(List<String> repos);
}
