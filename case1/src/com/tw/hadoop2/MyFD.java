package com.tw.hadoop2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class MyFD {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(MyFD.class);
        job.setJobName("friend");

        //2 设置输入输出路径
        Path inPath = new Path("/fd/input");
        FileInputFormat.addInputPath(job, inPath);

        Path outPath = new Path("/fd/output");
        if(outPath.getFileSystem(conf).exists(outPath))
            outPath.getFileSystem(conf).delete(outPath, true);
        FileOutputFormat.setOutputPath(job, outPath);

        job.setMapperClass(FMapper.class);
        job.setReducerClass(FReducer.class);

        job.waitForCompletion(true);
    }
}
