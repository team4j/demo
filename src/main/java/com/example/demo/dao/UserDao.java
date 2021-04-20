package com.example.demo.dao;

import com.example.demo.model.User;

/**
 * Created by dequan.yu on 2021/4/1.
 */
public interface UserDao {
    User getUserById(Integer id);
}
