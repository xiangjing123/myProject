package com.xj.project.thread;

import java.util.HashMap;
/**
 * 返回对象
 *
 * @author xiangjing
 * @date 2017/12/11
 * @company 天极云智
 */
public class Result extends HashMap<String,Object> {

    public Result put(String key,Object value){
        super.put(key,value);
        return this;
    }

    public static void main(String[] args) {
        String str1 = new String("abc");
        String str2 ="abc";
        StringBuilder sb  =new StringBuilder();
        System.out.println(str1 == str2);
    }

}
