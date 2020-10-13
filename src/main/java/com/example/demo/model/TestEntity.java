package com.example.demo.model;

import com.example.demo.enums.SexEnum;
import lombok.Data;

/**
 * Created by dequan.yu on 2020/10/13.
 */
@Data
public class TestEntity {
    private String name;

    private SexEnum sex;
}
