package com.example.demo.service;

import com.example.demo.dao.TaskDao;
import com.example.demo.dao.UserDao;
import com.example.demo.mapper.TaskMapper;
import com.example.demo.mapper.TestMapper;
import com.example.demo.model.Task;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashMap;

/**
 * Created by dequan.yu on 2020/8/19.
 */
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@SpringBootTest
public class TaskServiceTest {
    private final TaskService taskService;

    @MockBean
    private UserDao userDao;

    @MockBean
    private TaskMapper taskMapper;

    @MockBean
    private TaskDao taskDao;

    @MockBean
    private TestMapper testMapper;

    @Test
    public void selectByPrimaryKey() {
        Task task = this.taskService.selectByPrimaryKey(1);
        Assertions.assertNotNull(task);
    }

    @Test
    public void testMock() {
        Mockito.when(taskMapper.selectById(1)).thenReturn(Task.builder().id(1).name("任务一").status(1).build());

        Assertions.assertNotNull(taskMapper.selectById(1));
//        Mockito.when(taskDao.selectById(1)).thenReturn(new Task());
        Task task = this.taskService.selectByPrimaryKey(1);
        Assertions.assertNotNull(task);
    }

    @Test
    public void getResults() {
        Mockito.when(testMapper.getResults(1)).thenReturn(new HashMap<>());

        Assertions.assertNotNull(testMapper.getResults(1));
        Assertions.assertNotNull(this.taskService.getResults(1));
    }
}