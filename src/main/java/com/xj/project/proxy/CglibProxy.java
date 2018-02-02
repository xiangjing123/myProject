package com.xj.project.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * clig代理
 *
 * @author xiangjing
 * @date 2018/1/15
 * @company 天极云智
 */
public class CglibProxy implements MethodInterceptor {

    //维护目标对象
    private Object target;

    public CglibProxy(Object target) {
        this.target = target;
    }

    //给目标对象创建一个代理对象
    public Object getProxyInstance(){
        //1.工具类
        Enhancer en = new Enhancer();
        //2.设置父类
        en.setSuperclass(target.getClass());
        //3.设置回调函数
        en.setCallback(this);
        //4.创建子类(代理对象)
        return en.create();

    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("开始代理");
        method.invoke(target,objects);
        System.out.println("结束代理");
        return null;
    }

    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy(new DogImpl());
        DogImpl dog=(DogImpl)cglibProxy.getProxyInstance();
        dog.printAnimalName("ccc");
    }
}
