package com.xj.project.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * ces
 *
 * @author xiangjing
 * @date 2017/12/18
 * @company 天极云智
 */
public class MyClass {
    volatile int count = 0;
    private void writeln(String msg1) {
        System.out.println(msg1);
    }
    private void write(String msg1) {
        System.out.print(msg1);
    }
    private Object locka = new Object();
    private Object lockb = new Object();

    public synchronized void log1(String msg1) {
        count++;
        write(msg1);

        writeln(count+"");
    }

    public void log2(String msg1) {
        synchronized (this) {
            count++;
            write(msg1);
            writeln(count+"");
        }
    }
    public static void main(String[] args){
        new MyClass().deadLock();
    }

    private void deadLock(){
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (locka){
                    try{
                        System.out.println(Thread.currentThread().getName()+" get locka ing!");
                        Thread.sleep(500);
                        System.out.println(Thread.currentThread().getName()+" after sleep 500ms!");
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+" need lockb!Just waiting!");
                    synchronized (lockb){
                        System.out.println(Thread.currentThread().getName()+" get lockb ing!");
                    }
                }
            }
        },"thread1");

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lockb){
                    try{
                        System.out.println(Thread.currentThread().getName()+" get lockb ing!");
                        Thread.sleep(500);
                        System.out.println(Thread.currentThread().getName()+" after sleep 500ms!");
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+" need locka! Just waiting!");
                    synchronized (locka){
                        System.out.println(Thread.currentThread().getName()+" get locka ing!");
                    }
                }
            }
        },"thread2");

        thread1.start();
        thread2.start();

    }
}
