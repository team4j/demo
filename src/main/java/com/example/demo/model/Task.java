package com.example.demo.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("task")
public class Task implements Serializable {
    @TableId
    private Integer id;

    private String name;

    private Byte status;

    private static final long serialVersionUID = 1L;
}