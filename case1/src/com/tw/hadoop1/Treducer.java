package com.tw.hadoop1;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class Treducer extends Reducer<Tq, IntWritable, Text, IntWritable> {

    Text tkey = new Text();
    IntWritable tval = new IntWritable();

    @Override
    protected void reduce(Tq key, Iterable<IntWritable> vals, Context context) throws IOException, InterruptedException {
        int flag = 0;
        int day = 0;
        for (IntWritable val : vals) {
            if(flag == 0){
                tkey.set(key.toString());
                tval.set(val.get());
                context.write(tkey, tval);
                flag ++;
                day = key.getDay();
            }

            if(flag != 0 && day != key.getDay()){
                tkey.set(key.toString());
                tval.set(val.get());
                context.write(tkey, tval);
                return;
            }
        }
    }
}
