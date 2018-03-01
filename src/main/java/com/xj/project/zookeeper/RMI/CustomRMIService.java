package com.xj.project.zookeeper.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 自定义的Rim 接口实现
 *
 * @author xiangjing
 * @date 2018/2/28
 * @company 天极云智
 */
public interface CustomRMIService extends Remote {

    String sayHello(String name) throws RemoteException;
}
