package com.xj.project.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * Lockdemo
 *
 * @author xiangjing
 * @date 2017/12/20
 * @company 天极云智
 */
public class LockSupportDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable()
        {
            private int count = 0;

            @Override
            public void run()
            {
                long start = System.currentTimeMillis();
                long end = 0;

                while ((end - start) <= 1000)
                {
                    count++;
                    end = System.currentTimeMillis();
                }

                System.out.println("after 1 second.count=" + count);

                //等待或许许可
                LockSupport.park();
                System.out.println("thread over." + Thread.currentThread().isInterrupted());

            }
        });

        t.start();

        Thread.sleep(2000);

        System.out.println(LockSupport.getBlocker(Thread.currentThread()));
        // 中断线程
        t.interrupt();


        System.out.println("main over");

    }
}
