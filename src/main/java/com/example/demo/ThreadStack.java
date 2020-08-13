package com.example.demo;

/**
 * Created by dequan.yu on 2020/7/16.
 */
public class ThreadStack {
    Object obj0 = new Object();
    Object obj1 = new Object();

    public void fun0() {
        synchronized (obj0) {
            fun1();
        }
    }

    public void fun1() {
        synchronized (obj1) {
            while (true) {
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        ThreadStack stack = new ThreadStack();
        stack.fun0();
    }
}
