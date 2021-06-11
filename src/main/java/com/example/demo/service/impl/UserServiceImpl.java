package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by dequan.yu on 2021/4/1.
 */
@Service
public class UserServiceImpl implements UserService {
//    @Autowired
//    private UserDao userDao;

    @Override
    public User getUserById(Integer id) {
//        return this.userDao.getUserById(id);
        return null;
    }
}
