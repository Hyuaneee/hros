package com.haoqi.hros.service;

import com.haoqi.hros.model.Employee;
import com.haoqi.hros.model.RespPageBean;
import com.haoqi.hros.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class EmpadvancedService {


    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    OplogService oplogService;
    @Autowired
    EmployeeRecycleService employeeRecycleService;


    public RespPageBean getAllsEmpadvanced(Integer page, Integer size, Employee employee, Date[] beginDateScope) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Employee> data = employeeMapper.getAllsEmpadvanced(page, size, employee, beginDateScope);
        Long total = employeeMapper.getTotalE(employee, beginDateScope);
        return new RespPageBean(total, data);
    }
}
