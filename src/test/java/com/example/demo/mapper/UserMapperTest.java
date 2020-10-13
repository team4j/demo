package com.example.demo.mapper;

import com.example.demo.enums.Sex;
import com.example.demo.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by dequan.yu on 2020/10/11.
 */
@Slf4j
@EnableTransactionManagement
@MapperScan("com.example.demo.mapper")
@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class UserMapperTest {
    private final UserMapper userMapper;

    @Test
    public void insert() {
        User user = User.builder().age(11).name("as").email("11").sex(Sex.MALE).build();
        int result = this.userMapper.insert(user);
        Assertions.assertEquals(1, result);
    }

    @Test
    public void select() {
        User user = this.userMapper.selectById(1315986653778276354L);
        log.info(user.toString());
        Assertions.assertNotNull(user);
    }

}