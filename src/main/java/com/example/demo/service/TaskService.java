package com.example.demo.service;

import com.example.demo.model.Task;

import java.util.Map;

/**
 * Created by dequan.yu on 2020/8/19.
 */
public interface TaskService {
    Task selectByPrimaryKey(Integer id);

    void updateStatus();

    Map<String, Object> getResults(int id);
}
