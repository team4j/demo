package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * @author dequan.yu
 * @version V1.0
 * @description TODO
 * @date 2019/11/27
 **/
@Service
public class OrderService {
    @Bean
    public Order getOrder() {
        return new Order();
    }
}
