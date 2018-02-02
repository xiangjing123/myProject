package com.xj.project.Callable;

import com.sun.jmx.snmp.tasks.ThreadService;
import com.sun.org.apache.xalan.internal.utils.FeatureManager;

import java.util.Date;
import java.util.concurrent.*;

/**
 * Callable 线程
 *
 * @author xiangjing
 * @date 2017/12/21
 * @company 天极云智
 */
public class ThreadTest {
    public static String sharedVariable;//共享变量
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {


        ExecutorService taske = Executors.newSingleThreadExecutor();
        Future<String> future =taske.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                TimeUnit.MILLISECONDS.sleep(2000);
                return "ces";
            }
        });
        System.out.println("开始时间："+System.currentTimeMillis());
        System.out.println(future.get());


        System.out.println("结束时间:"+System.currentTimeMillis());
        taske.shutdown();
    };
}
