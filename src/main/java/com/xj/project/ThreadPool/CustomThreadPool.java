package com.xj.project.ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 自定义线程池
 *
 * @author xiangjing
 * @date 2017/12/20
 * @company 天极云智
 */
public class CustomThreadPool implements Executor {

    private boolean isStop =Boolean.FALSE;

    private List<WorkThread> threads = null;

    private BlockingQueue<Runnable> taskQueue = null;

    private static int default_threads_num = 5;

    private int maxNums=default_threads_num;

    protected  String test1;

      String  test2;


    public CustomThreadPool(){
        this(default_threads_num);
    }

    public CustomThreadPool(int threadNums){
        this.maxNums = threadNums;
        threads =new ArrayList<>();
        taskQueue =new LinkedBlockingDeque<>();
        if(this.maxNums <0) throw new IllegalStateException("thread num can't is zero");

        for(int i=0; i<this.maxNums;i++){
            threads.add(new WorkThread(taskQueue));
        }
        startThread();

    }

    @Override
    public synchronized void execute(Runnable thread) {

        if(this.isStop) throw new IllegalStateException("CustomThreadPool is stop");
        this.taskQueue.add(thread);

    }

    public synchronized void toStop() throws InterruptedException {
        for(WorkThread thread:threads){
            thread.toStop();
        }
        this.isStop = true;

    }
    public synchronized void startThread(){
        for(WorkThread thread:threads){
            thread.start();
        }
    }

    public  Boolean getIsStop(){
        return this.isStop;
    }

    public BlockingQueue<Runnable> getTaskQueue() {
        return taskQueue;
    }

    public void setTaskQueue(BlockingQueue<Runnable> taskQueue) {
        this.taskQueue = taskQueue;
    }

    public List<WorkThread> getThreads() {
        return threads;
    }

    public void setThreads(List<WorkThread> threads) {
        this.threads = threads;
    }
}
class WorkThread extends Thread{

    private BlockingQueue<Runnable> taskQueue =null;

    private boolean  isStop = Boolean.FALSE;

    public WorkThread(BlockingQueue<Runnable> queue){
        this.taskQueue = queue;
    }
    @Override
    public void run() {

        while(!isStop){
            try {
                Runnable runnable=taskQueue.take();
                runnable.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public synchronized void toStop(){
        if(this.isStop) throw new IllegalStateException(Thread.currentThread().getName()+",is stop");
        this.isStop =true;
        this.interrupt();
    }

    public  Boolean getIsStop(){
       return this.isStop;
    }
}

