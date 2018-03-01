package com.xj.project.zookeeper.rmi;

import java.rmi.Naming;

/**
 * rmi 客户端测试
 *
 * @author xiangjing
 * @date 2018/2/28
 * @company 天极云智
 */
public class RmiClient {

    public static void main(String[] args) throws Exception {
        String url =String.format("rmi://localhost:1099/%s",HelloServiceImpl.class.getName());
        CustomRMIService helloService= (CustomRMIService)Naming.lookup(url);
        System.out.println(helloService.hashCode());
        System.out.println(helloService.sayHello("xiangjing"));;
    }
}
