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
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

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
    private final TransactionTemplate transactionTemplate;

    @Test
    void deleteByPrimaryKey() {
    }

    @Test
    void insert() {
        Task task = Task.builder().name("任务").status(0).build();
        int result = this.taskMapper.insert(task);
        Assertions.assertEquals(1, result);
    }

    @Test
    void insertSelective() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            Task task = Task.builder().name("任务" + i).status(0).build();
            int result = this.taskMapper.insert(task);
            Assertions.assertEquals(1, result);
        }

        long end = System.currentTimeMillis();
        log.info("耗时：{}ms", end - start);
    }

    @Test
    void selectByPrimaryKey() {
        Task task = this.taskMapper.selectById(1);
        Assertions.assertNotNull(task);
    }

    @Test
    void updateByPrimaryKeySelective() {
    }

    @Test
    void updateByPrimaryKey() {
    }

    @Test
    public void getTaskList() {
        List<Task> taskList = this.taskMapper.getTaskList();
        Assertions.assertTrue(taskList.size() > 0);
    }

    @Test
    public void tx() {
        // 有结果返回
        String result = transactionTemplate.execute(action -> {
            Task task = Task.builder().name("任务二").status(1).tag("测试").build();
            this.taskMapper.insert(task);
//            System.out.println(1 / 0);
            return "哈哈";
        });
        log.info(result);

        // 没有结果返回
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                innerTx();
                System.out.println(1 / 0);
            }
        });

        log.info("结束");
    }

    @Rollback(value = false)
    @Transactional
    public void innerTx() {
        Task task = Task.builder().name("任务二").status(1).tag("测试").build();
        taskMapper.insert(task);
    }
}