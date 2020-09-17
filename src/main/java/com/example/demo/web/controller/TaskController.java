package com.example.demo.web.controller;

import com.example.demo.model.Task;
import com.example.demo.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dequan.yu on 2020/8/19.
 */
@Slf4j
@RestController
@RequestMapping("/task")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TaskController {
    private final TaskService taskService;

    ExecutorService executorService = Executors.newFixedThreadPool(4);

    @GetMapping("/get/{id}")
    public Task get(@PathVariable Integer id) {
//        log.info("发起请求");
        return taskService.selectByPrimaryKey(id);
    }

    @GetMapping("/exec")
    public String exec() {
        for (int i = 0; i < 4; i++) {
            executorService.submit(() -> {
                try {
                    log.info("睡眠了");
                    Thread.sleep(100 * 1000);
                    log.info("睡醒了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        return "SUCCESS";
    }
}
