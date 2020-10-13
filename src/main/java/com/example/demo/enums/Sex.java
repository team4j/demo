package com.example.demo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

/**
 * Created by dequan.yu on 2020/10/13.
 */
public enum Sex {
    MALE(1, "男"), FEMALE(0, "女")
    ;

    @EnumValue
    private final Integer code;
    private final String desc;

    @JsonValue
    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    Sex(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @JsonCreator
    public static Sex getByCode(int code) {
        for (Sex value : Sex.values()) {
            if (Objects.equals(code, value.getCode())) {
                return value;
            }
        }
        return null;
    }
}
