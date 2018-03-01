package com.xj.project.zookeeper.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * hello RMI 实现
 *
 * @author xiangjing
 * @date 2018/2/28
 * @company 天极云智
 */
public class HelloServiceImpl  extends UnicastRemoteObject implements CustomRMIService {


    //private static final long serialVersionUID = -7603835623116684897L;

    public HelloServiceImpl() throws RemoteException {
    }

    @Override
    public String sayHello(String name) throws RemoteException {
        return String.format("hello %s",name);
    }
}
