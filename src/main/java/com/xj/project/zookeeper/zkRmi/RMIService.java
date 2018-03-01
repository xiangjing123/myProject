package com.xj.project.zookeeper.zkRmi;

/**
 * RMIservice
 *
 * @author xiangjing
 * @date 2018/3/1
 * @company 天极云智
 */
public class RMIService {

    public static void main(String[] args) throws Exception {
        ServiceProvider provider = new ServiceProvider();
        provider.createService(32);
    }
}
