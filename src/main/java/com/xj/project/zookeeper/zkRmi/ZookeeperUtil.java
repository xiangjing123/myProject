package com.xj.project.zookeeper.zkRmi;

import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * zookeeper 工具类
 *
 * @author xiangjing
 * @date 2018/2/28
 * @company 天极云智
 */
public class ZookeeperUtil {

    private static final Logger logger = LoggerFactory.getLogger(ZookeeperUtil.class);
    private CountDownLatch latch = new CountDownLatch(1);

    private ZooKeeper zk=null;

    public static List<String> urls =Collections.emptyList();

    private ZooKeeper connectService(){
        try {
            zk = new ZooKeeper(Zookeeper_RMI.ZK_CONNECTION_STRING,Zookeeper_RMI.ZK_SESSION_TIMEOUT,new  Watcher(){
                @Override
                public void process(WatchedEvent event) {
                    if(event.getState() ==Event.KeeperState.SyncConnected){
                        latch.countDown();
                    }
                }
            });
            latch.await();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return zk;
    }

    public void createNode(String path,String data){//创建节点
        ZooKeeper zk = connectService();
        try {
            zk.create(path,data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE , CreateMode.EPHEMERAL_SEQUENTIAL);
        } catch (KeeperException e) {
            logger.error(e.getMessage());
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }

    public void create(String data){
        createNode(Zookeeper_RMI.ZK_PROVIDER_PATH,data);
    }

    public void wathChild(String path){
        ZooKeeper zk = connectService();
        if( null != zk){
            try {
                List<String> str=zk.getChildren(path, new Watcher() {
                    @Override
                    public void process(WatchedEvent event) {

                        System.out.println(event.getType() == Event.EventType.NodeChildrenChanged);
                        if(event.getType() == Event.EventType.NodeChildrenChanged){

                            wathChild(path);
                        }
                    }
                });
                System.out.println(str);
                List<String> dataList = new ArrayList<>(); // 用于存放 /registry 所有子节点中的数据
                for(String node:str){
                    byte[] data = zk.getData(Zookeeper_RMI. ZK_REGISTRY_PATH + "/" + node, false , null);
                    dataList.add(new String(data));
                }
                urls=dataList;
            } catch (KeeperException e) {
                logger.error(e.getMessage());
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
        }
    }
}
