package com.haoqi.hros.service;

import com.haoqi.hros.mapper.EmployeeremoveMapper;
import com.haoqi.hros.model.Employeeremove;
import com.haoqi.hros.service.utils.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class EmployeeremoveService {

    @Autowired
    EmployeeremoveMapper employeeremoveMapper;
    @Autowired
    OplogService oplogService;
    public Integer addEmploymove(Employeeremove employeeremove) {
       oplogService.addOpLog(LogUtils.addLog((byte) 6,"员工调动:eid="+employeeremove.getEid()));
       return employeeremoveMapper.insertSelective(employeeremove);
    }
}
