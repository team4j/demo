package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dequan.yu
 * @version V1.0
 * @description TODO
 * @date 2019/11/27
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer id;
    private String source;
}
