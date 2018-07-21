package com.neu.tom.zk;

import com.google.common.collect.Lists;
import com.neu.tom.ZkConfig;
import com.neu.tom.zk.loadbalance.LoadBanalce;
import com.neu.tom.zk.loadbalance.RandomLoadBanalce;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.List;

/**
 * 查找服务实现
 * Author: Wu Shuai
 * Date: 2018/7/21
 */
public class ServiceDiscoveryImpl implements IServiceDiscovery {

    private CuratorFramework curatorFramework;

    private List<String> repos = Lists.newArrayList();

    public ServiceDiscoveryImpl() {
        this.curatorFramework = CuratorFrameworkFactory.builder().
                connectString(ZkConfig.CONNNECTION_STR).
                sessionTimeoutMs(4000).
                retryPolicy(new ExponentialBackoffRetry(1000,
                        10)).build();
        curatorFramework.start();
    }

    @Override
    public String discover(String serviceName) {
        String path = ZkConfig.ZK_REGISTER_PATH + "/" + serviceName;
        try {
            repos = curatorFramework.getChildren().forPath(path);
        } catch (Exception e) {
            throw new RuntimeException("获取子节点异常：" + e);
        }
        //动态发现服务节点的变化
        registerWatcher(path);

        //负载均衡机制
        LoadBanalce loadBanalce = new RandomLoadBanalce();
        return loadBanalce.selectHost(repos); //返回调用的服务地址
    }

    /**
     * 动态发现服务节点的变化
     *
     * @param path
     */
    private void registerWatcher(final String path) {
        PathChildrenCache childrenCache = new PathChildrenCache(curatorFramework, path, true);
        PathChildrenCacheListener childrenCacheListener = new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                repos = curatorFramework.getChildren().forPath(path);
            }
        };
        childrenCache.getListenable().addListener(childrenCacheListener);
        try {
            childrenCache.start();
        } catch (Exception e) {
            throw new RuntimeException("注册PatchChild Watcher异常" + e);
        }
    }
}
