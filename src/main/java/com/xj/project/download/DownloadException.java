package com.xj.project.download;

/**
 * 下载异常
 *
 * @author xiangjing
 * @date 2018/1/15
 * @company 天极云智
 */
public class DownloadException  extends RuntimeException{

    /**
     * 异常信息
     */
    private String msg;

    public DownloadException(String msg) {
        super(msg);
    }

    public DownloadException(String msg,Throwable throwable){
        super(msg,throwable);
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
