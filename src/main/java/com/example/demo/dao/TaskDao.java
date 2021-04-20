package com.example.demo.dao;


import com.example.demo.model.Task;

/**
 * Created by dequan.yu on 2021/4/1.
 */
public interface TaskDao {
    Task selectById(Integer id);
}
