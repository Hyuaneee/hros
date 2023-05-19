package com.haoqi.hros.controller.emp;

import com.haoqi.hros.model.Employee;
import com.haoqi.hros.model.RespPageBean;
import com.haoqi.hros.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/employee/advanced")
public class EmpadvancedController {

    @Autowired
    EmpadvancedService empadvancedService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    NationService nationService;
    @Autowired
    PoliticsstatusService politicsstatusService;
    @Autowired
    JobLevelService jobLevelService;
    @Autowired
    PositionService positionService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    EmployeeecService employeeecService;

    @GetMapping("/")
    public RespPageBean getAllsEmpadvanced(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, Employee employee, Date[] beginDateScope){
        return empadvancedService.getAllsEmpadvanced(page, size, employee, beginDateScope);
    }

}
