package com.example.demo.dao;

import com.example.demo.mapper.TaskMapper;
import com.example.demo.model.Task;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by dequan.yu on 2020/8/18.
 */
@MapperScan("com.example.demo.mapper")
@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class TaskDaoTest {
    private final TaskMapper taskMapper;

    @Test
    void deleteByPrimaryKey() {
    }

    @Test
    void insert() {
    }

    @Test
    void insertSelective() {
    }

    @Test
    void selectByPrimaryKey() {
        Task task = this.taskMapper.selectByPrimaryKey(1);
        Assertions.assertNotNull(task);
    }

    @Test
    void updateByPrimaryKeySelective() {
    }

    @Test
    void updateByPrimaryKey() {
    }
}