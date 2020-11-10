package com.example.demo.web.controller;

import com.example.demo.dto.EnumRequest;
import com.example.demo.dto.EnumResponse;
import com.example.demo.enums.State;
import com.example.demo.model.Task;
import com.example.demo.service.TestService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by dequan.yu on 2020/9/19.
 */
//@EnableAsync
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {
    private final TestService testService;

    @PostMapping("/testEnum")
    public EnumResponse testEnum(@RequestBody EnumRequest request) {
        Gson gson = new Gson();
//        log.info(gson.toJson(request));
        return EnumResponse.builder().state(State.INIT).build();
    }

    @PostMapping("/testListReq")
    public List<Task> testListReq(@RequestBody List<Task> taskList) {
        return taskList;
    }

    @GetMapping("/testAsync")
    public String testAsync() throws ExecutionException, InterruptedException {
        log.info("开始testAsync");
        CompletableFuture<String> task = testService.task();
//        if (task.isDone()) {
//            log.info("任务执行结果：{}", task.get());
//        }
        task.thenAccept(s -> log.info("获取异步执行结果：{}", s));

        log.info("结束testAsync");
        return "success";
    }
}
