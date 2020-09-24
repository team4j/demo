package com.example.demo.dto;

import com.example.demo.enums.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by dequan.yu on 2020/9/19.
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EnumResponse implements Serializable {
    private State state;
}
