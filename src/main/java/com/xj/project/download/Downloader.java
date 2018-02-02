package com.xj.project.download;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 下载器
 *
 * @author xiangjing
 * @date 2018/1/15
 * @company 天极云智
 */
public class Downloader {


    /**
     * 默认下载路径
     */
    protected static final String default_saveUrl = "D:/XJ/download";

    /**
     * 默认下载路径
     */
    protected static final int default_timeOut = 30000;

    /**
     * 下载中文件的格式
     */
    protected static final String format = " .download";

    /**
     * 是否停止
     */
    private boolean isStop = false;

    /**
     * 下载线程
     */
    private Thread downloadThread;


    /**
     * 下载路径
     */
    private String downloadUrl;

    /**
     * 保存路径
     */
    private String saveUrl;

    /**
     * 断点
     */
    private long breakePoint;

    /**
     * 下载速度
     */
    private int timeOut;

    private URLConnection connection;

    private InputStream is;


    public Downloader(String downloadUrl, String saveUrl, int timeOut, long breakePoint) {
        this.downloadUrl = downloadUrl;
        this.saveUrl = saveUrl;
        this.timeOut = timeOut;
        this.breakePoint = breakePoint;
    }

    public Downloader(String downloadUrl, String saveUrl) {
        this.downloadUrl = downloadUrl;
        this.saveUrl = saveUrl;
        this.timeOut = default_timeOut;
    }

    public Downloader(String downloadUrl) {
        this.downloadUrl = downloadUrl;
        this.saveUrl = default_saveUrl;
        this.timeOut = default_timeOut;
    }

    private void initDownloader() {
        if (null == this.downloadUrl || this.downloadUrl.length() == 0) {
            throw new DownloadException("下载路径不能为空");
        }
        try {
            URL url = new URL(this.downloadUrl);
            this.connection = url.openConnection();
            connection.setRequestProperty("Range", "bytes=" + breakePoint + "-");
            this.connection.setConnectTimeout(timeOut);
            this.connection.connect();
        } catch (Exception e) {
            throw new DownloadException(this.downloadUrl, e);
        }
    }

    private String getCachePath(){
        return this.saveUrl + "/" + this.downloadUrl.substring(this.downloadUrl.lastIndexOf("/") + 1, this.downloadUrl.length()) + this.format;
    }

    private String getPath(){
        return this.saveUrl + "/" + this.downloadUrl.substring(this.downloadUrl.lastIndexOf("/") + 1, this.downloadUrl.length());
    }

    /**
     * 创建缓存文件
     */
    private OutputStream createCacheFile() throws IOException {
        String path = getCachePath();

        File rootDir = new File(this.saveUrl);

        if (!rootDir.exists()) {
            rootDir.mkdirs();
        }

        File svaePath = new File(path);

        if (!svaePath.exists()) {
            svaePath.createNewFile();
        }

        return new FileOutputStream(svaePath);
    }

    private void createSaveFile() throws IOException {
        String cacheFile = getCachePath();
        String formalFile = getPath();
        Files.copy(Paths.get(cacheFile),Paths.get(formalFile));
        File file = new  File(cacheFile);
        file.delete();

    }

    /**
     * 开始下载
     */
    private synchronized void doDownload() throws IOException {
        this.initDownloader();//初始化
        is = connection.getInputStream();

        byte[] b = new byte[1024];

        OutputStream os = createCacheFile();

        this.downloadThread= new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程开始下载");
                int len = 0;
                try {
                    while ((len = is.read(b)) != -1 && !isStop) {
                        breakePoint+= len;
                        os.write(b,0,len);
                    }
                    if(connection.getContentLengthLong() <= breakePoint){
                        System.out.println("====="+downloadUrl+"下载完成");
                        os.flush();
                        os.close();
                        is.close();
                        createSaveFile();
                    }else{
                        if(isStop){
                            os.flush();
                            os.close();
                            is.close();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        this.downloadThread.start();
    }

    /**
     * 停止下载
     * @return
     */
    public synchronized long parse() throws InterruptedException {
        this.isStop = true;
        this.downloadThread.join();
        System.out.println(this.downloadThread.getState());
        return this.breakePoint;
    }

    /**
     * 开始下载
     * @param breakePoint
     * @throws IOException
     */
    public synchronized void start(long breakePoint) throws IOException {
        this.breakePoint = breakePoint;
        start();
    }

    /**
     * 开始下载
     * @throws IOException
     */
    public synchronized void start() throws IOException {
        this.isStop = false;
        this.doDownload();

    }

    public long getBreakePoint() {
        return breakePoint;
    }

    public void setBreakePoint(long breakePoint) {
        this.breakePoint = breakePoint;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public boolean isStop() {
        return isStop;
    }

    public void setIsStop(boolean isStop) {
        this.isStop = isStop;
    }
}
