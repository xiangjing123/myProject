package com.xj.project.hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * 统计单词map实现
 *
 * @author xiangjing
 * @date 2018/2/9
 * @company 天极云智
 */
public class CountMapperImpl extends Mapper<IntWritable,Text,Text,IntWritable> {

    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    @Override
    protected void map(IntWritable key, Text value, Context context) throws IOException, InterruptedException {
        StringTokenizer itr = new StringTokenizer(value.toString());
        System.out.println(itr);
        while(itr.hasMoreTokens()){
            word.set(itr.nextToken());
            context.write(value,one);
        }
    }
}


