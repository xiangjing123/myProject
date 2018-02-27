package com.xj.project.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.CommonConfigurationKeysPublic;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * 测试
 *
 * @author xiangjing
 * @date 2018/2/26
 * @company 天极云智
 */
public class WordCountJob2 {

    private static final String hdfsUrl="hdfs://192.168.139.112:9000";
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException, ClassNotFoundException {

        Configuration conf = new Configuration();
        conf.set(CommonConfigurationKeysPublic.FS_DEFAULT_NAME_KEY,hdfsUrl);
        conf.set("mapreduce.input.fileinputformat.split.maxsize",19+"");//设置split 最大的值
        Job job=Job.getInstance(conf,"word count");
        job.setJarByClass(WordCountJob2.class);


        FileInputFormat.addInputPath(job, new Path("/hdfs/word.txt"));
        FileInputFormat.setMinInputSplitSize(job,2);
        FileSystem fileSystem = FileSystem.get(new URI(hdfsUrl),conf,"xiangjing");
        Path outPut =new Path("/out2");
        if(fileSystem.exists(outPut)){
            fileSystem.delete(outPut,true);
        }
        FileOutputFormat.setOutputPath(job,outPut);
        job.setMapperClass(CountMapperImpl.class);
        job.setCombinerClass(IntSumReducer.class);
        job.setReducerClass(IntSumReducer.class);
        job.setOutputKeyClass(Text.class);
      /*  job.setPartitionerClass(HashPartitioner.class);*/
        job.setPartitionerClass(HashPartitioner.class);
        job.setOutputValueClass(IntWritable.class);
        job.setNumReduceTasks(1);
        job.waitForCompletion(true);


    }
}
