package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * task
 * @author 
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Task implements Serializable {
    private Integer id;

    private String name;

    private Byte status;

    private static final long serialVersionUID = 1L;
}