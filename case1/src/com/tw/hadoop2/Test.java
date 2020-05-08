package com.tw.hadoop2;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        /*Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();*/

        int num1;
        int num2;
        for (int input = 1; input < 200; input++) {
            if (input <= 9) {
                if (input == 4 || input == 9)
                    System.out.println(1+">"+input);
                else if (input == 8)
                    System.out.println(2+">"+input);
                else
                    System.out.println(-1+">"+input);
            } else {
                num1 = input % 9;
                num2 = input / 9;
                if (num1 == 0)
                    System.out.println(num2 +">"+input);
                else {
                    if (num1 % 4 == 0)
                        System.out.println((num2 + num1 / 4) + ">"+input);
                    else
                        System.out.println(-1+">"+input);
                }
            }
        }
    }
}
