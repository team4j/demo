package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * Created by dequan.yu on 2021/4/1.
 */
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserDao userDao;

    @Test
    public void getUserById() throws Exception {
        // 定義當調用mock userDao的getUserById()方法，並且參數為3時，就返回id為200、name為I'm mock3的user對象
        Mockito.when(this.userDao.getUserById(3)).thenReturn(new User());

        // 返回的會是名字為I'm mock 3的user對象
        User user = userService.getUserById(3);

        Assertions.assertNotNull(user);
//        Assertions.assertEquals(user.getId(), new Integer(200));
//        Assertions.assertEquals(user.getName(), "I'm mock 3");
    }
}
