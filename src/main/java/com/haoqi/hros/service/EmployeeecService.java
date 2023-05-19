package com.haoqi.hros.service;

import com.haoqi.hros.model.Employeeec;
import com.haoqi.hros.service.utils.LogUtils;
import com.haoqi.hros.mapper.EmployeeecMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;


@Service
public class EmployeeecService {

    @Autowired
    EmployeeecMapper employeeecMapper;

    @Autowired
    OplogService oplogService;
    public Integer addEmployeeec(Employeeec employeeec, Integer[] ids) {
        employeeec.setEcdate(new Date());
        oplogService.addOpLog(LogUtils.addLog((byte) 3,"员工奖惩添加:eid="+ employeeec.getEid()+"--ids:"+ Arrays.toString(ids)));
      return   employeeecMapper.insertSelectives(employeeec,  ids);


    }

    public Integer deleteEmpByEidc(Integer eid, Integer id) {
        oplogService.addOpLog(LogUtils.addLog((byte) 3,"员工奖惩删除:eid="+ eid+"--id:"+ id));
        return employeeecMapper.deleteEmpByEidc(eid, id);
    }
}
