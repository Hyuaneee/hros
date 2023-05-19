package com.haoqi.hros.service;


import com.haoqi.hros.mapper.DepartmentMapper;
import com.haoqi.hros.model.Department;
import com.haoqi.hros.model.OpLog;
import com.haoqi.hros.service.utils.Hruitls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Transactional
@Service
public class DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    OplogService oplogService;



    public List<Department> getAllDepartments() {
        return departmentMapper.getAllDepartmentsByParentId(-1);
    }

    public void addDep(Department dep) {
        dep.setEnabled(true);
        departmentMapper.addDep(dep);
        oplogService.addOpLog(new OpLog((byte) 1,new Date(),"添加部门:" + dep.getName(), Hruitls.getCurrent().getName()));
    }

    public void deleteDepById(Department dep) {
        departmentMapper.deleteDepById(dep);
        oplogService.addOpLog(new OpLog((byte) 1,new Date(),"删除部门:id=" + dep.getId(), Hruitls.getCurrent().getName()));
    }


    public List<Department> getAllDepartmentsWithOutChildren() {
        return departmentMapper.getAllDepartmentsWithOutChildren();
    }
}
