package com.example.demo;

/**
 * Created by dequan.yu on 2020/6/10.
 */
public class CalcStep {
    public static void main(String[] args) {
        System.out.println(new CalcStep().calc(10000));
    }

    public int calc(int n) {
        if (n - 1 == 0) {
            return 1;
        }

        if (n -2 == 0) {
            return 2;
        }
        return calc(n - 1) + calc(n - 2);
    }
}
