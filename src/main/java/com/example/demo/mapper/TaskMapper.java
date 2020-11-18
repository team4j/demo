package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.model.Task;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TaskMapper extends BaseMapper<Task> {
    @Select("SELECT * FROM task ORDER BY id DESC LIMIT 10")
    List<Task> getTaskList();
}