package com.example.demo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by dequan.yu on 2021/6/11.
 */
@NoArgsConstructor
@Data
public class RestResponse {

    private String result;
    private Object errCode;
    private String errmsg;
    private Object row;
    private Integer total;
}
