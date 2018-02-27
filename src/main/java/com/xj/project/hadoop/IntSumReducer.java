package com.xj.project.hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapreduce.Counter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Iterator;

/**
 * reduce实现
 *
 * @author xiangjing
 * @date 2018/2/9
 * @company 天极云智
 */
public class IntSumReducer extends org.apache.hadoop.mapreduce.Reducer<Text,IntWritable,Text,IntWritable> {

    private  static  final Logger logger = LoggerFactory.getLogger(IntSumReducer.class);
    private IntWritable result = new IntWritable();

    public void reduce(Text key, Iterable<IntWritable> values,
                       Context context
    ) throws IOException, InterruptedException {
        int sum = 0;
        Counter counter =context.getCounter("Custom group","Key number");
        counter.increment(1);
        for (IntWritable val : values) {
            sum += val.get();
        }
        result.set(sum);
        context.write(key, result);
    }
}
