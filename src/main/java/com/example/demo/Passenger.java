package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by dequan.yu on 2020/6/10.
 */
@AllArgsConstructor
@Data
public class Passenger {
    private Integer id;
    private Integer orderId;
    private String name;
}
