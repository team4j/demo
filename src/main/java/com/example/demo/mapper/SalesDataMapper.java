package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.model.SalesDataDO;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by dequan.yu on 2021/1/29.
 */
public interface SalesDataMapper extends BaseMapper<SalesDataDO> {
    @Select("SELECT *  FROM sales_data WHERE create_time < #{localDateTime}")
    List<SalesDataDO> getAllByCreateTime(LocalDateTime localDateTime);
}