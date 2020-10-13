package com.example.demo.enums;

/**
 * Created by dequan.yu on 2020/10/13.
 */


import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;


import java.util.Objects;

/**
 * //TODO
 *
 * @author zhuquanwen
 * @vesion 1.0
 * @date 2020/4/5 15:23
 * @since jdk1.8
 */

public enum  SexEnum /*implements IEnum<Integer>*/ {

    /**
     * 男
     * */
    MAN(1, "男"),

    /**
     * 女
     * */
    WOMEN(2, "女");


    @EnumValue
    private final int code;

    @JsonValue
    public int getCode() {
        return this.code;
    }

    public String getDescription() {
        return description;
    }

    private final String description;
    SexEnum(int val, String description) {
        this.code = val;
        this.description = description;
    }

    @JsonCreator
    public static SexEnum getByCode(int code) {
        for (SexEnum value : SexEnum.values()) {
            if (Objects.equals(code, value.getCode())) {
                return value;
            }
        }
        return null;
    }
/*
    @Override
    public Integer getValue() {
        return code;
    }*/
}

