package com.xj.project.jvm;

import java.util.Objects;

/**
 * 测试
 *
 * @author xiangjing
 * @date 2018/3/14
 * @company 天极云智
 */
public class Test {
    public static void main(String[] args) {

    /*    int x=11,y=10;
        System.out.println(x+","+y);

        Runnable runnable =() -> System.out.println(Thread.currentThread().getName()+"hellow");
        new Thread(runnable).start();
        TestInterface testInterface=()-> System.out.println("hello");
        testInterface.run();*/
        Integer x = null;
        Integer y = new Integer(1000);
        Integer z = new Integer(1000);
       /* System.out.println( 1000 == x);*/
        System.out.println( 1000 == y);
        System.out.println( x == y);
        System.out.println(Objects.equals(x,1000));
        String s = new String("123");
        String s1 = new String("123") ;
        System.out.println(s1 ==s);
        System.out.println(y == 1000);
        System.out.println( x instanceof  Integer);

    }
}
