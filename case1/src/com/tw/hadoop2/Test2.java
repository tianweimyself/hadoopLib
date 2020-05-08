package com.tw.hadoop2;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Test2 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int row = scan.nextInt();
        String[] strs = new String[row];
        for (int i = 0; i < row; i++) {
            strs[i] = scan.next();
        }
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < row; i++) {
            char[] ch = strs[i].toCharArray();
            for (int j = 0; j < ch.length; j++) {
                if(j == '='){
                    String[] split = strs[i].split("=");
                    if(StringUtils.isNumeric(split[1])){
                        map.put(split[0], Integer.valueOf(split[1]));
                    }
                }else if(j == '+'){
                    String[] strings = strs[i].split("\\+");
                    String[] strings1 = strings[0].split("=");
                    int result = map.get(strings1[1]);
                    for (int k = 1; k < strings.length; k++) {
                        result += map.get(strings[k]);
                    }

                    System.out.println(result);

                }
            }

        }
    }
}
