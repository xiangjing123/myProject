package com.xj.project.zookeeper.zkRmi;

/**
 * rmi 常用
 *
 * @author xiangjing
 * @date 2018/2/28
 * @company 天极云智
 */
public interface Zookeeper_RMI {
    String ZK_CONNECTION_STRING = "hadoop.qiansitong.com:50032,hadoop.qiansitong.com:50033";
    int ZK_SESSION_TIMEOUT = 5000;
    String ZK_REGISTRY_PATH = "/registry";
    String ZK_PROVIDER_PATH = ZK_REGISTRY_PATH + "/provider" ;
}
