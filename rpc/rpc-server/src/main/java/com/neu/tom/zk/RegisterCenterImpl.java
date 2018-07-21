package com.neu.tom.zk;

import com.neu.tom.ZkConfig;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * 注册中心zookeeper实现
 * Author: Wu Shuai
 * Date: 2018/7/21
 */
public class RegisterCenterImpl implements IRegisterCenter {

    private CuratorFramework curatorFramework;

    public RegisterCenterImpl() {
        this.curatorFramework = CuratorFrameworkFactory.builder().
                connectString(ZkConfig.CONNNECTION_STR).
                sessionTimeoutMs(4000).
                retryPolicy(new ExponentialBackoffRetry(1000,
                        10)).build();
        curatorFramework.start();
    }

    @Override
    public void register(String serviceName, String serviceAddress) throws Exception {
        String servicePath = ZkConfig.ZK_REGISTER_PATH + "/" + serviceName;
        if (curatorFramework.checkExists().forPath(servicePath) == null) {
            curatorFramework.create().withMode(CreateMode.PERSISTENT).forPath(servicePath, "0".getBytes());
        }
        String addressPath = servicePath + "/"+ serviceAddress;
        String rsNode = curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath(addressPath,"0".getBytes());
        System.out.println("服务注册成功：" + rsNode);
    }
}
