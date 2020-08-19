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

/**
 * Created by dequan.yu on 2020/8/19.
 */
@Slf4j
@RestController
@RequestMapping("/task")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/get/{id}")
    public Task get(@PathVariable Integer id) {
//        log.info("发起请求");
        return taskService.selectByPrimaryKey(id);
    }
}
