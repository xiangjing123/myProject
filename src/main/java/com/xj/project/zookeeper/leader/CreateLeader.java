package com.xj.project.zookeeper.leader;

import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;

import java.util.List;

/**
 * 创建领导者模式
 *
 * @author xiangjing
 * @date 2018/3/2
 * @company 天极云智
 */
public class CreateLeader {

    public static void main(String[] args) {
        String masterPath = "/master_path";
        CuratorFramework client = CuratorFrameworkFactory.builder().
                connectString("hadoop.qiansitong.com:50032").
                sessionTimeoutMs(5000).
                connectionTimeoutMs(10000).
                retryPolicy(new RetryPolicy() {

                    @Override
                    public boolean allowRetry(int i, long l, RetrySleeper retrySleeper) {
                        return false;
                    }
                }).
                namespace("text").build();
        client.start();
        /**
         * 该实例封装了所有Master选举相关的逻辑，包括所有和Zookeeper服务器交互的过程，其中Master_select代表一个Master选举的一个
         * 根节点，表明本次Master选举都是在该节点下进行的。
         * 在创建LeaderSelector实例的时候，还会传入一个监听器:LeaderSelectorListenerAdapter。这需要开发人员自行实现。Curator
         * 会在成功获取Master权利时候回调该监听器。
         */
        LeaderSelector leaderSelector = new LeaderSelector(client, masterPath, new LeaderSelectorListener() {

            @Override
            public void stateChanged(CuratorFramework client, ConnectionState stat) {

            }

            /**
             *  成为Master角色
             *  完成Master操作，释放Master权利
             *  成为Master角色
             */
            @Override
            public void takeLeadership(CuratorFramework client) throws Exception {
                System.out.println("成为Master");
                Thread.sleep(3000);
                List<String> path = client.getChildren().forPath(masterPath);
                System.out.println(path);
                System.out.println("完成Master操作，释放Master权利");
            }
        });
        leaderSelector.autoRequeue();
        leaderSelector.start();

    }
}
