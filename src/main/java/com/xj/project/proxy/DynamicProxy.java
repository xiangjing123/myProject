package com.xj.project.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 *
 * @author xiangjing
 * @date 2018/1/15
 * @company 天极云智
 */
public class DynamicProxy {
    private  static  final Logger logger = LoggerFactory.getLogger(DynamicProxy.class);

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        ProxyInterface target = new DogImpl();
        ProxyInterface proxy= (ProxyInterface)Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                logger.info("开始代理");
                method.invoke(target, args);
                logger.info("结束代理");
                return null;
            }
        });
        proxy.printAnimalName("hh");

    }
}
