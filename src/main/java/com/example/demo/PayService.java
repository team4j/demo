package com.example.demo;

import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 * Created by dequan.yu on 2019/12/4.
 */
@Service
public class PayService {

    @Retryable
    public void pay() {
        System.out.println("pay...");
    }
}
