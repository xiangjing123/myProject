package com.xj.project.zookeeper.zkRmi;

/**
 * RMI 客户端
 *
 * @author xiangjing
 * @date 2018/3/1
 * @company 天极云智
 */
public class RMIClient {
    public static void main(String[] args) throws Exception {
        ServerConsumer consumer = new ServerConsumer();
        while (true){
            try{
                consumer.lookup();

            }catch (Exception e){

            }
            Thread.sleep(3000);
        }

    }
}
