package com.xj.project.thread;

/**
 * a线程
 *
 * @author xiangjing
 * @date 2017/12/12
 * @company 天极云智
 */
public class ThreadB implements Runnable {

    private Object obj;

    public ThreadB(Object obj) {
        this.obj = obj;
    }

    @Override
    public void run() {
        synchronized (obj){
            int i=0;
            while(i<20){
                try {
                    Thread.sleep(2222);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("B线程打印=" + i);
                i++;
            }
        }
    }
}
