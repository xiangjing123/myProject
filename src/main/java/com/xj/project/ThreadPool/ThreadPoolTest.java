package com.xj.project.ThreadPool;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * 线程池测试
 *
 * @author xiangjing
 * @date 2017/12/20
 * @company 天极云智
 */
public class ThreadPoolTest {

    private int i;
    public static void main(String[] args) throws ExecutionException, InterruptedException, NoSuchFieldException, IllegalAccessException {
        /*CustomThreadPool pool =new CustomThreadPool();

        Thread thread =new Thread(()->System.out.print(Thread.currentThread().getName()+"测试打印"));
        pool.execute(thread);
        System.out.println("sss"+TimeUnit.SECONDS.toNanos(10L));
*/
        Thread thread =new Thread(()->System.out.print(Thread.currentThread().getName()+"测试打印"));
        ExecutorService service = Executors.newSingleThreadExecutor();
        /*service.execute(thread);*/
        Future<String> future = service.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                return Thread.currentThread().getName() + "测试打印";
            }
        });
        System.out.println(future.get());
        service.shutdown();

        Field field = Unsafe.class.getDeclaredField("theUnsafe");

        field.setAccessible(true);
        Unsafe unsafe = (Unsafe)field.get(null);
        ThreadPoolTest test = new ThreadPoolTest();
        long offset = unsafe.objectFieldOffset(ThreadPoolTest.class.getDeclaredField("i"));
        unsafe.putInt(test,offset,100);
        System.out.println(test.i);


    }


}
