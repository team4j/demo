package com.example.demo.service;

import java.util.concurrent.CompletableFuture;

/**
 * Created by dequan.yu on 2020/11/9.
 */
public interface TestService {
    CompletableFuture<String> task() throws InterruptedException;
}
