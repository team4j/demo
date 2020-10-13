package com.example.demo.model;

import com.example.demo.enums.Sex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by dequan.yu on 2020/10/11.
 */
@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private Sex sex;
}