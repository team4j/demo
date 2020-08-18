package com.example.demo;

import java.util.concurrent.*;

/**
 * Created by dequan.yu on 2020/8/17.
 */
public class TheadTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Object> callable = Object::new;
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Object> future = executorService.submit(callable);
        Object obj = future.get();
        System.out.println(obj);
    }
}
