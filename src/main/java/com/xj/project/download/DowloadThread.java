package com.xj.project.download;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 下载测试主线程
 *
 * @author xiangjing
 * @date 2018/1/4
 * @company 天极云智
 */
public class DowloadThread {

    public static void main(String[] args) throws Exception {
  /*      URL url = new URL("http://vphoto.kepuchina.cn/images/20179201505883858203_1_1.jpg");
        URLConnection con = url.openConnection();
        //con.connect();
        FileOutputStream out = new FileOutputStream("d:/110.jpg");
        InputStream ins = con.getInputStream();
        byte[] b = readInputStream(ins);
        System.out.println("b="+b.length);
        int i=0;
        out.write(b);
        out.flush();
        ins.close();
        out.close();
        */
        Downloader downloader = new Downloader("http://vskd.bj.bcebos.com/video/2017/9/20/20179201505864824880_39_1724.mp4");
        downloader.start();
        Thread.sleep(1000);
        System.out.println(System.currentTimeMillis()+"文件暂停");
        long brekepoitn =downloader.parse();

        Thread.sleep(1000);
        System.out.println(System.currentTimeMillis()+"文件继续下载");
        downloader.start(brekepoitn);
        System.out.println(System.currentTimeMillis());
        String fileName ="http://localhost:8080/中文.jpg";
        new String(fileName.getBytes("gbk"),"utf-8");






    }


    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
           bos.write(buffer, 0, len);
            System.out.println(len);
        }
        return bos.toByteArray();
    }

    private static void printArray(byte[] b){
        StringBuffer sb = new StringBuffer();
        int count = 0;
        for(int i=0;i<b.length;i++){
            sb.append(b[i]+",");
            count++;

        }
        System.out.println(sb.toString()+count);
    }


}
