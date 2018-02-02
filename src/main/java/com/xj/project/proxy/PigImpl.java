package com.xj.project.proxy;

/**
 * 猪实现
 *
 * @author xiangjing
 * @date 2018/1/15
 * @company 天极云智
 */
public class PigImpl implements ProxyInterface {
    @Override
    public void printAnimalName(String arg) {
        System.out.println("my name is pig,"+arg);
    }
}
