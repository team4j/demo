package com.example.demo.web.controller;

import com.example.demo.model.TestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dequan.yu on 2020/10/13.
 */
@RestController
@RequestMapping("/testEntity")
public class TestMpEnumController {

    @PostMapping("/post")
    public ResponseEntity testSaveEntity(@RequestBody TestEntity testEntity) {

        return ResponseEntity.ok("");
    }

}