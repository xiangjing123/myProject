package com.xj.project.thread;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.locks.Lock;

/**
 * 测试线程同步
 *
 * @author xiangjing
 * @date 2017/12/18
 * @company 天极云智
 */
public class ThreadSys  {


     int count = 0;
    public   void count(){
        synchronized (this){
            count++;
        }

    }
    public  synchronized  void delete(){

        synchronized(this){
            count--;
        }

    }
    public void recursiveMethod(){
       while(count <100000){
           count++;
           System.out.println("执行了:"+count+"次");
       }

       // recursiveMethod();//递归调用
    }
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        //获取Unsafe实例
        Field f = Unsafe.class.getDeclaredField("theUnsafe"); // Internal reference
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);

        //获取字段i在内存中偏移量
        long offset = unsafe.objectFieldOffset(UnsafeDemo.class.getDeclaredField("i"));

        System.out.println("offset:"+offset);
        //创建对象实例，设置字段的值
        ThreadSys unsafeDemo = new ThreadSys();
        unsafe.putInt(unsafeDemo, offset, 100);

        //打印结果
        System.out.println(unsafeDemo.count);
    }
}
