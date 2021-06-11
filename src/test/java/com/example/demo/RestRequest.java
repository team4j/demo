package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by dequan.yu on 2021/6/11.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RestRequest {

    private Integer id;
    private String reason;
    private Integer reasonType;
}
