package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.model.Task;

public interface TaskMapper extends BaseMapper<Task> {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Task record);

    Task selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);
}