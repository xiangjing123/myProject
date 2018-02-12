package com.xj.project.hadoop;

import com.xj.project.log4j2.Log4jTwoTest;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.io.IOUtils;
import org.junit.BeforeClass;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

/**
 * hdfs 文件系统测试
 *
 * @author xiangjing
 * @date 2018/2/7
 * @company 天极云智
 */
public class HDFSTest extends Log4jTwoTest {
    public static String nameNodeUrl="hdfs://47.95.240.71:9000";
    public static Configuration configuration=new Configuration();
    public static FileSystem fileSystem;

    public static void init() throws Exception{
        Configuration configuration=new Configuration();
        configuration.set("fs.defaultFS", "hdfs://47.95.240.71:9000");
       System.setProperty("hadoop.home.dir", "D:\\hadoop-plugin");
        URI uri = new URI(nameNodeUrl);
        String user="root";
        fileSystem=FileSystem.get(uri,configuration,user);
        System.out.println(fileSystem);
    }

    public static void mkdirs(String file) throws IOException {
        Path path=new Path(file);
        final boolean exists = fileSystem.exists(path);
        System.out.println(exists);//创建前
        if(!exists){
            fileSystem.mkdirs(path);
        }

        System.out.println(fileSystem.exists(path));//创建后
    }
    public static void touchFile() throws IOException {
        Path path=new Path("/hdfs/javaapi/test.txt");
        final boolean exists = fileSystem.exists(path);
        System.out.println(exists);//创建前
        if(!exists){
            fileSystem.create(path,true);
        }
        System.out.println(fileSystem.exists(path));//创建后

    }
    public static void putFile() throws IOException {
        Path path=new Path("/hdfs/javaapi/test.txt");
        OutputStream fost=null;
        fost=fileSystem.create(path,true);
        ByteArrayInputStream in = new ByteArrayInputStream("测试一下".getBytes());//输入流
        IOUtils.copyBytes(in,fost,1024,true);
        System.out.println(fileSystem.exists(path));//创建后

    }

    private static void listFiles(FileStatus fileStatus) throws IOException {
        Path path = fileStatus.getPath();
        System.out.println(path);
        if (!fileStatus.isDirectory()) {// 如果不是目录

            FsPermission permission = fileStatus.getPermission();
            String owner = fileStatus.getOwner();
            short replication = fileStatus.getReplication();
            long len = fileStatus.getLen();
            System.out.println("path:"+path+",permission:"+permission+",replication:"
                    +replication+",owenr:"+owner+",size:"+len);

        } else {// 如果是目录
            FileStatus[] listStatus = fileSystem.listStatus(path);
            for (FileStatus childStatus : listStatus) {
                listFiles(childStatus);
            }
        }
    }
    public static void deleteFile() throws IOException {
        Path path=new Path("/hdfs/javaapi/test.txt");
        final boolean exists = fileSystem.exists(path);
        System.out.println(exists);//创建前
        if(!exists){
            fileSystem.delete(path, true);
        }
        System.out.println(fileSystem.exists(path));//创建后

    }
    public static void getFile() throws IOException {
        Path path=new Path("/hdfs/javaapi/test.txt");
        final boolean exists = fileSystem.exists(path);
        System.out.println(exists);//创建前
        if(!exists){
            fileSystem.getFileChecksum(path);
        }
        System.out.println(fileSystem.exists(path));//创建后

    }
    public static void main(String[] args) throws Exception {
        init();
        putFile();

    }
}
