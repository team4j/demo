package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    private OrderService orderService;

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
}
