package com.xj.project.zookeeper.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * RMI 测试
 *
 * @author xiangjing
 * @date 2018/2/28
 * @company 天极云智
 */
public class RmiService {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
       int port=1099;
        String url =String.format("rmi://localhost:1099/%s",HelloServiceImpl.class.getName());
        LocateRegistry. createRegistry(port);
        HelloServiceImpl helloService = new HelloServiceImpl();
        System.out.println(helloService.hashCode());
        Naming.rebind(url,helloService);

    }
}
