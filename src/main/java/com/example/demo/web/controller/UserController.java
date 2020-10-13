package com.example.demo.web.controller;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by dequan.yu on 2020/10/13.
 */
@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserController {
    private final UserMapper userMapper;

    @GetMapping("/list")
    public ResponseEntity<Object> list() {
        List<User> testEntities = this.userMapper.selectList(null);
        return ResponseEntity.ok(testEntities);
    }
    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody User user) {
        this.userMapper.insert(user);
        return ResponseEntity.ok(user);
    }
}
