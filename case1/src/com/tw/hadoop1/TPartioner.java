package com.tw.hadoop1;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class TPartioner extends Partitioner<Tq, IntWritable> {
    @Override
    public int getPartition(Tq key, IntWritable value, int i) {
        return key.getYear() % i;
    }
}
