package com.example.demo;

import lombok.Data;

import java.util.Date;

/**
 * @author dequan.yu
 * @version V1.0
 * @description TODO
 * @date 2019/11/19
 **/
@Data
public class Coup {
    private Long id;
    private String couponName;
    private String giftCard;
    private String batchNumber;
    private Integer couponHolder;
    private Integer amount;
    private String cityId;
    private String cityName;
    private String productId;
    private String carTypeId;
    private String describe;
    private Integer status;
    private String operator;
    private Date createTime;
    private Integer carRate;
    private Integer otherRate;
    private Integer supplierRate;
    private Integer userRange;
    private Integer couponResource;
    private String supplierCode;
}
