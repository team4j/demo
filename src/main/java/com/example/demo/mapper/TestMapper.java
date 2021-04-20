package com.example.demo.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * Created by dequan.yu on 2021/4/2.
 */
public interface TestMapper {
    @Select("SELECT * FROM task WHERE id = #{id}")
    Map<String, Object> getResults(@Param("id") int id);
}
