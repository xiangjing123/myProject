package com.xj.project.zookeeper.zkRmi;

import com.xj.project.zookeeper.rmi.CustomRMIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Collections;
import java.util.List;

/**
 * 服务消费者
 *
 * @author xiangjing
 * @date 2018/2/28
 * @company 天极云智
 */
public class ServerConsumer {

    private static final Logger logger = LoggerFactory.getLogger(ZookeeperUtil.class);

    private ZookeeperUtil zookeeperUtil = new ZookeeperUtil();

    private List<String> urls = null;

    public ServerConsumer() {
        zookeeperUtil.wathChild(Zookeeper_RMI.ZK_REGISTRY_PATH);
    }

    public void lookup() throws Exception{
        urls= ZookeeperUtil.urls;
        if(urls.size() == 0 ){
            logger.error("zooker 连接失败");
            return;
        }
        String url = urls.get(0);
        logger.info("url="+url);
        CustomRMIService customRMIService = (CustomRMIService)Naming.lookup(url);
        System.out.println(customRMIService.sayHello("hello word"));;
    }
}
