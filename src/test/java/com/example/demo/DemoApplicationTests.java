package com.example.demo;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.validation.Validator;

@EnableRetry
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    private OrderService orderService;

    @Autowired
    private Validator validator;

    @Autowired
    @Lazy
    private PayService payService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void getOrder() {
        Order order = orderService.getOrder();
        System.out.println(order);
    }

    @Test
    public void pay() {
        payService.pay();
    }

    public static class Admin {
        private String name;
    }
}
