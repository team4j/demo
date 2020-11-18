package com.example.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * source
 * @author 
 */
@Data
@TableName("source")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Source implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer whId;

    private Integer goodsId;

    private String dateTime;

    private Integer storeCount;

    private String tag;

    private String logTraceId;

    private static final long serialVersionUID = 1L;
}