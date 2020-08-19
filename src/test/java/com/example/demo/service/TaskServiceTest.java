package com.example.demo.service;

import com.example.demo.model.Task;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by dequan.yu on 2020/8/19.
 */
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@MapperScan("com.example.demo.mapper")
@SpringBootTest
class TaskServiceTest {
    private final TaskService taskService;

    @Test
    void selectByPrimaryKey() {
        Task task = this.taskService.selectByPrimaryKey(1);
        Assertions.assertNotNull(task);
    }
}