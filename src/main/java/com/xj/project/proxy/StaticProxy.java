package com.xj.project.proxy;

/**
 * 静态代理
 *
 * @author xiangjing
 * @date 2018/1/15
 * @company 天极云智
 */
public class StaticProxy implements ProxyInterface{

    private ProxyInterface proxyInterface;

    public StaticProxy(ProxyInterface proxyInterface) {
        this.proxyInterface = proxyInterface;
    }

    @Override
    public void printAnimalName(String arg) {
        System.out.println("代理开始");
        proxyInterface.printAnimalName("hhh");
        System.out.println("代理结束");
    }

    public static void main(String[] args) {
        StaticProxy proxy = new StaticProxy(new DogImpl());
        proxy.printAnimalName("fff");
    }
}
