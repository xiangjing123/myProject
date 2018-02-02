package com.xj.project.thread;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程测试类
 *
 * @author xiangjing
 * @date 2017/12/5
 * @company 天极云智
 */
public class ThreadTest extends Thread {

    static   int count =0;

    public static void main(String[] args) throws InterruptedException {

        MyClass myClass =new MyClass();
        ThreadSys ts = new ThreadSys();
        ThreadSys ts1 = new ThreadSys();
        Lock lock =new ReentrantLock();
        long start =System.currentTimeMillis();
        Thread t=new Thread(() ->{

                lock.lock();//添加锁
                System.out.println(Thread.currentThread().getName()+"得到了锁");
                try{
                    for(int i=0;i<100000;i++){
                    count++;
                    }
                }catch (Exception e){

                }finally {
                    lock.unlock();//释放锁
                    System.out.println(Thread.currentThread().getName()+"释放了锁");
                }




        });


        t.start();

            lock.lock();//添加锁
            System.out.println(Thread.currentThread().getName()+"得到了锁");
            try{
                for(int i=0;i<100000;i++){
                count++;
                }
            }catch (Exception e){

            }finally {
                lock.unlock();//释放锁
                System.out.println(Thread.currentThread().getName()+"释放了锁");
            }

        t.join();

        System.out.println(count);

    }

}
