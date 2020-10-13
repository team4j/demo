package com.example.demo.dao;

import com.example.demo.mapper.TaskMapper;
import com.example.demo.model.Task;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by dequan.yu on 2020/8/18.
 */
@Slf4j
@EnableTransactionManagement
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
        Task task = Task.builder().name("任务").status((byte) 0).build();
        int result = this.taskMapper.insert(task);
        Assertions.assertEquals(1, result);
    }

    @Test
    void insertSelective() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            Task task = Task.builder().name("任务" + i).status((byte) 0).build();
            int result = this.taskMapper.insertSelective(task);
            Assertions.assertEquals(1, result);
        }

        long end = System.currentTimeMillis();
        log.info("耗时：{}ms", end - start);
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