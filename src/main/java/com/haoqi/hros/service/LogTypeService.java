package com.haoqi.hros.service;

import com.haoqi.hros.mapper.LogTypeMapper;
import com.haoqi.hros.model.LogType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LogTypeService {

    @Autowired
    LogTypeMapper logTypeMapper;


    public List<LogType> getAllsLogtype() {
        return logTypeMapper.selectByPrimaryKey();
    }
}
