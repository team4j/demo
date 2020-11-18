package com.example.demo.service.impl;

import com.example.demo.mapper.TaskMapper;
import com.example.demo.model.Task;
import com.example.demo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dequan.yu on 2020/8/19.
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TaskServiceImpl implements TaskService {
    private final TaskMapper taskMapper;

    @Override
    public Task selectByPrimaryKey(Integer id) {
        return taskMapper.selectById(id);
    }

    @Override
    public void updateStatus() {

    }
}
