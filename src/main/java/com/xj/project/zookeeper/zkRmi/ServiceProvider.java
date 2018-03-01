package com.xj.project.zookeeper.zkRmi;

import com.xj.project.zookeeper.rmi.HelloServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * rmi 服务发布者
 *
 * @author xiangjing
 * @date 2018/2/28
 * @company 天极云智
 */
public class ServiceProvider {

    private ZookeeperUtil zookeeperUtil = new ZookeeperUtil();

    private static final Logger logger = LoggerFactory.getLogger(ServiceProvider.class);

    public void createService() throws Exception {
        int port=1099;
        String url =String.format("rmi://localhost:1099/%s",HelloServiceImpl.class.getName());
        LocateRegistry. createRegistry(port);
        HelloServiceImpl helloService = new HelloServiceImpl();
        Naming.rebind(url, helloService);
        logger.debug("publish rmi service (url: {})" , url );
        zookeeperUtil.create(url);

    }

    public void createService(int port) throws Exception {
         port=1100;
        String url =String.format("rmi://localhost:%d/%s",port,HelloServiceImpl.class.getName());
        LocateRegistry. createRegistry(port);
        HelloServiceImpl helloService = new HelloServiceImpl();
        Naming.rebind(url, helloService);
        logger.debug("publish rmi service (url: {})" , url );
        zookeeperUtil.create(url);

    }
}
