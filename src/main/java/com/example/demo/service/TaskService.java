package com.example.demo.service;

import com.example.demo.model.Task;

/**
 * Created by dequan.yu on 2020/8/19.
 */
public interface TaskService {
    Task selectByPrimaryKey(Integer id);
}
