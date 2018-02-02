package com.xj.project.proxy;

/**
 * dog 实现
 *
 * @author xiangjing
 * @date 2018/1/15
 * @company 天极云智
 */
public class DogImpl implements ProxyInterface {
    @Override
    public void printAnimalName(String arg) {
        System.out.println("my name is dog,"+arg);
    }
}
