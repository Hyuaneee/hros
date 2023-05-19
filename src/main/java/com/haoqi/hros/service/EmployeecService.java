package com.haoqi.hros.service;

import com.haoqi.hros.model.datas.DataModel;
import com.haoqi.hros.mapper.EmployeeecMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeecService {

    @Autowired
    EmployeeecMapper employeeecMapper;

    public List<DataModel> getModelandView() {
        return employeeecMapper.getModelandView();
    }
}
