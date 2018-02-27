package com.xj.project.hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * 统计单词map实现
 *
 * @author xiangjing
 * @date 2018/2/9
 * @company 天极云智
 */
public class CountMapperImpl extends Mapper<LongWritable,Text,Text,IntWritable> {

    private  static  final Logger logger = LoggerFactory.getLogger(IntSumReducer.class);
    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        StringTokenizer itr = new StringTokenizer(value.toString());
        logger.error("key="+key);
        Counter counter = context.getCounter("Custom group","Sensitive word");
        while(itr.hasMoreTokens()){
            String nextToken = itr.nextToken();

            word.set(nextToken);
            if(nextToken.equals("hello")){
                counter.increment(1);
            }
            context.write(word,one);
        }
    }
}


