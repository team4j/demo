package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dequan.yu on 2020/8/28.
 */
@Slf4j
public class ThreadPoolTest {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        int size = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(size);
        try {
            for (int i = 0; i < size; i++) {
                executorService.execute(() -> {
                    int j = RandomUtils.nextInt(0, 3);
                    try {
                        if (j == 1) {
                            throw new RuntimeException("哈哈哈哈哈");
                        }
                        System.out.println(j);
                    } catch (RuntimeException e) {
                        log.error(e.getMessage(), e);
                    }
                });
//                sb.append(future.get(10, TimeUnit.SECONDS));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(sb.toString());
//        executorService.shutdown();
    }
}
