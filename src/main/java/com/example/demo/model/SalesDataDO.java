package com.example.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by dequan.yu on 2021/1/29.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sales_data")
public class SalesDataDO {
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    @TableField(value = "goods_id")
    private Integer goodsId;

    @TableField(value = "wh_id")
    private Integer whId;

    @TableField(value = "sales")
    private Integer sales;

    @TableField(value = "sales_time")
    private LocalDateTime salesTime;

    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @TableField(value = "tag")
    private String tag;
}