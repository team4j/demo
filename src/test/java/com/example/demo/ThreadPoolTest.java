package com.example.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dequan.yu on 2020/3/10.
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(() -> {
            System.out.println("我是一个线程池");
        });
    }
}
