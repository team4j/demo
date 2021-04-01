package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * Created by dequan.yu on 2021/3/17.
 */
@Slf4j
public class RootTest {
    @MockBean
    private Object object;

    @Test
    void mockito() {
        System.out.println(object);
    }
}
