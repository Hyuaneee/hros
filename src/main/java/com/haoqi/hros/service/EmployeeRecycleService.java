package com.haoqi.hros.service;

import com.haoqi.hros.mapper.EmployeeRecycleMapper;
import com.haoqi.hros.model.Employee;
import com.haoqi.hros.model.EmployeeRecycle;
import com.haoqi.hros.model.datas.DataModel;
import com.haoqi.hros.model.datas.DataModels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeRecycleService {

    @Autowired
    EmployeeRecycleMapper employeeRecycleMapper;


    public void addEmployeeRecycle(Employee employee){
        employeeRecycleMapper.insertSelective(employee);

    }

    public List<EmployeeRecycle> getAllEmployeeRecycle() {
        return employeeRecycleMapper.selectByPrimaryKey();
    }

    public List<DataModel> dataViewDepartment() {
        return employeeRecycleMapper.dataViewDepartment();
    }

    public List<DataModels> dataViewPosition() {
        return employeeRecycleMapper.dataViewPosition();
    }

    public List<DataModels> dataViewJobLevelT() {
        return employeeRecycleMapper.dataViewJobLevelT();
    }

    public Integer delete(Integer id) {
        return employeeRecycleMapper.delete(id);
    }
}
