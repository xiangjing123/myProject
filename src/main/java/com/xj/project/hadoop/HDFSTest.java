package com.xj.project.hadoop;

import com.xj.project.log4j2.Log4jTwoTest;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.io.IOUtils;
import org.junit.BeforeClass;

import java.io.*;
import java.net.URI;
import java.util.Arrays;

/**
 * hdfs 文件系统测试
 *
 * @author xiangjing
 * @date 2018/2/7
 * @company 天极云智
 */
public class HDFSTest extends Log4jTwoTest {
    public static String nameNodeUrl = "hdfs://192.168.139.112:9000";
    public static Configuration configuration = new Configuration();
    public static FileSystem fileSystem;

    public static void init() throws Exception {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "hdfs://192.168.139.112:9000");
        URI uri = new URI(nameNodeUrl);
        String user = "xiangjing";
        fileSystem = FileSystem.get(uri, configuration, user);
        System.out.println(fileSystem);
    }

    public static void mkdirs(String file) throws IOException {
        Path path = new Path(file);
        final boolean exists = fileSystem.exists(path);
        System.out.println(exists);//创建前
        if (!exists) {
            fileSystem.mkdirs(path);
        }

        System.out.println(fileSystem.exists(path));//创建后
    }

    public static void touchFile() throws IOException {
        Path path = new Path("/hdfs/javaapi/test.txt");
        final boolean exists = fileSystem.exists(path);
        System.out.println(exists);//创建前
        if (!exists) {
            fileSystem.create(path, true);
        }
        System.out.println(fileSystem.exists(path));//创建后

    }

    public static void putFile(String uploadPath) throws IOException {
        String content = "hello you \n hello me";//文件内容
        boolean overwrite = true;//如果文件存在就覆盖
        final Path hdfsPath = new Path(uploadPath);
        FSDataOutputStream out = fileSystem.create(hdfsPath, overwrite);
        boolean close = true;//上传成功关闭输入流和输出流
        int bufferSize = 1024;//缓冲大小
        ByteArrayInputStream in = new ByteArrayInputStream(content.getBytes());//输入流
        IOUtils.copyBytes(in, out, bufferSize, close);
        System.out.println(fileSystem.exists(hdfsPath));//判断文件是否上传到HDFS上

    }

    private static void listFiles(FileStatus fileStatus) throws IOException {
        Path path = fileStatus.getPath();
        System.out.println(path);
        if (!fileStatus.isDirectory()) {// 如果不是目录

            FsPermission permission = fileStatus.getPermission();
            String owner = fileStatus.getOwner();
            short replication = fileStatus.getReplication();
            long len = fileStatus.getLen();
            System.out.println("path:" + path + ",permission:" + permission + ",replication:"
                    + replication + ",owenr:" + owner + ",size:" + len);

        } else {// 如果是目录
            FileStatus[] listStatus = fileSystem.listStatus(path);
            for (FileStatus childStatus : listStatus) {
                listFiles(childStatus);
            }
        }
    }

 /*   public static void deleteFile() throws IOException {
        Path path = new Path("/hdfs/javaapi/test.txt");
        final boolean exists = fileSystem.exists(path);
        System.out.println(exists);//创建前
        if (!exists) {
            fileSystem.delete(path, true);
        }
        System.out.println(fileSystem.exists(path));//创建后

    }*/

    public static void getFile() throws IOException {
        Path path = new Path("/hdfs/javaapi/test.txt");
        final boolean exists = fileSystem.exists(path);
        System.out.println(exists);//创建前
        if (!exists) {
            fileSystem.getFileChecksum(path);
        }
        System.out.println(fileSystem.exists(path));//创建后

    }

    public static void main(String[] args) throws Exception {
        init();
        /*mkdirs("/hdfs/test");*/
       //putFile("/hdfs/test/test.txt");
        //deleteFile(new Path("/hdfs/test"));
       // download(new Path("/hdfs/word.txt"));
       // listFiles(new Path("/"));
        // mkdirs("/hdfs/xj1");

    }

    public static void listFiles(Path path) throws Exception {
        FileStatus fileStatu = fileSystem.getFileStatus(path);
        Path path1 = fileStatu.getPath();
        if(fileStatu.isDirectory()){
            FileStatus[] fs =fileSystem.listStatus(path1);
            Arrays.asList(fs).forEach((f->{
                try {
                    listFiles(f.getPath());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }));
        }else {
            System.out.println(path1.getName()+":"+fileStatu.getOwner());
        }

    }
    public static void deleteFile(Path path) throws IOException {
        FileStatus fileStatus=fileSystem.getFileStatus(path);
        if(fileSystem.exists(path)){
            fileSystem.delete(path,true);//false 只能删除空目录，true 删除目录以及子文件或者目录
        }
    }

    public static void download(Path path) throws IOException {
        FileStatus fileStatu=fileSystem.getFileStatus(path);
        FSDataInputStream fsDataInputStream=fileSystem.open(path);
        if(fileStatu.isFile()){
            IOUtils.copyBytes(fsDataInputStream,System.out,10,true);
        }
    }
}
