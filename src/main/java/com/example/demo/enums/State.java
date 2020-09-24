package com.example.demo.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * Created by dequan.yu on 2020/9/19.
 */
@Getter
public enum State {
    INIT(2, "初始")
    ;
    @JsonValue
    private Integer state;
    private String desc;

    State(Integer state, String desc) {
        this.state = state;
        this.desc = desc;
    }
}
