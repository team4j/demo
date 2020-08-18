package com.example.demo.model;

import java.io.Serializable;
import lombok.Data;

/**
 * task
 * @author 
 */
@Data
public class Task implements Serializable {
    private Integer id;

    private String name;

    private Byte status;

    private static final long serialVersionUID = 1L;
}