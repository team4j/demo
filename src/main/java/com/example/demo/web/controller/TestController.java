package com.example.demo.web.controller;

import com.example.demo.dto.EnumRequest;
import com.example.demo.dto.EnumResponse;
import com.example.demo.enums.State;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dequan.yu on 2020/9/19.
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {
    @PostMapping("/testEnum")
    public EnumResponse testEnum(@RequestBody EnumRequest request) {
        Gson gson = new Gson();
//        log.info(gson.toJson(request));
        return EnumResponse.builder().state(State.INIT).build();
    }
}
