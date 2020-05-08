package com.tw.hadoop1;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Tmapper extends Mapper<LongWritable, Text, Tq, IntWritable> {

    Tq tkey = new Tq();
    IntWritable tval = new IntWritable();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //获得时间， 温度数组
        String[] words = StringUtils.split(value.toString(), '\t');
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            Date date = sdf.parse(words[0]);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);

            tkey.setYear(cal.get(Calendar.YEAR));
            tkey.setMonth(cal.get(Calendar.MONTH) + 1);
            tkey.setMonth(cal.get(Calendar.DAY_OF_MONTH));

            //处理温度
            int wd = Integer.parseInt(words[1].substring(0, words[1].lastIndexOf("c")));
            tkey.setWd(wd);
            tval.set(wd);
            context.write(tkey, tval);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
