package com.haoqi.hros.service;

import com.haoqi.hros.mapper.EmployeetrainMapper;
import com.haoqi.hros.model.Employeetrain;
import com.haoqi.hros.service.utils.LogUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Transactional
@Service
public class EmployeetrainService {

    @Autowired
    EmployeetrainMapper employeetrainMapper;
    @Autowired
    OplogService oplogService;

    public Integer addEmployeetrain(Employeetrain employeetrain) {
        oplogService.addOpLog(LogUtils.addLog((byte) 4,"添加培训:eid="+employeetrain.getEid()));
        return employeetrainMapper.insertSelective(employeetrain);
    }

    public Integer updateEmployeetrain(Employeetrain employeetrain) {
        oplogService.addOpLog(LogUtils.addLog((byte) 4,"员工培训eid:" + employeetrain.getEid()));
        return employeetrainMapper.updateByPrimaryKeySelective(employeetrain);
    }

    public Integer deleteEmployeetrain(Integer eid, Integer id) {
        oplogService.addOpLog(LogUtils.addLog((byte) 4,"取消员工培训eid:" +eid));
        return employeetrainMapper.deleteByEmployeetrain( eid, id);
    }

    public Integer addEmploteetByPge(Employeetrain employeetrain, Integer[] ids) {
          oplogService.addOpLog(LogUtils.addLog((byte) 4,"批量添加培训:"));
          return employeetrainMapper.addEmploteetByPge(employeetrain, ids);

    }


}


