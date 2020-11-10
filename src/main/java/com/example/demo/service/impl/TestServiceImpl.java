package com.example.demo.service.impl;

import com.example.demo.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * Created by dequan.yu on 2020/11/9.
 */
@Slf4j
@Service
public class TestServiceImpl implements TestService {
    @Async("taskExecutor")
    @Override
    public CompletableFuture<String> task() throws InterruptedException {
        log.info("异步任务执行开始");
        Thread.sleep(10 * 1000);
        log.info("异步任务执行结束");
        return CompletableFuture.completedFuture("异步任务执行结束");
    }
}
