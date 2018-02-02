package com.xj.project.thread;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Unsafe测试
 *
 * @author xiangjing
 * @date 2017/12/19
 * @company 天极云智
 */
public class UnsafeDemo {

    public String test;

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Field field=Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
       Unsafe unsafes=(Unsafe)field.get(null);
       // Unsafe unsafes =Unsafe.getUnsafe();
        UnsafeDemo threadSys = new UnsafeDemo();
        long offset = unsafes.objectFieldOffset(threadSys.getClass().getField("test"));
        unsafes.putObject(threadSys,offset,"hhh");
        System.out.println(threadSys.test);

    }
}
