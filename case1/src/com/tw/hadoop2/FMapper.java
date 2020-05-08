package com.tw.hadoop2;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    Text tkey = new Text();
    IntWritable tval = new IntWritable();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] words = StringUtils.split(value.toString(), ' ');
        for (int i = 1; i < words.length; i++) {
            tkey.set(getFd(words[0], words[i]));
            //如果直接好友，输出0
            tval.set(0);
            context.write(tkey, tval);
            for (int j = i+1; j < words.length; j++) {
                tkey.set(getFd(words[j], words[i]));
                //间接好友，输出1
                tval.set(1);
                context.write(tkey, tval);
            }
        }
    }

    private String getFd(String a, String b) {
        return a.compareTo(b) > 0 ? b+":"+a : a+":"+b;
    }
}
