package com.haoqi.hros.controller.salary;

import com.haoqi.hros.model.Employee;
import com.haoqi.hros.model.RespPageBean;
import com.haoqi.hros.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/salary/table")
public class SalartTableController {

    @Autowired
    EmployeeService employeeService;
    @GetMapping("/")
    public RespPageBean getAllsalarts(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, Employee employee, Date[] beginDateScope){
       return employeeService.getAllsalarts(page,size,employee,beginDateScope);
    }
}
