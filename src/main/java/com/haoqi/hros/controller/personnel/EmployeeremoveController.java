package com.haoqi.hros.controller.personnel;

import com.haoqi.hros.model.*;
import com.haoqi.hros.service.DepartmentService;
import com.haoqi.hros.service.EmployeeService;
import com.haoqi.hros.service.EmployeeremoveService;
import com.haoqi.hros.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/personnel/remove")
public class EmployeeremoveController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    PositionService positionService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    EmployeeremoveService employeeremoveService;

    @GetMapping("/")
    public RespPageBean getEmploteeByPge(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, Employee employee, Date[] beginDateScope) {
        return employeeService.getEmploteeByPgeM(page, size, employee, beginDateScope);

    }

    @GetMapping("/positions")
    public List<Position> getAllPositions() {
        return positionService.getAllPositions();
    }


    @GetMapping("/deps")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @PutMapping("/")
    public RespBean updateEmp(@RequestBody Employee employee) {
        Employeeremove employeeremove = new Employeeremove(employee.getId(),employee.getDepartmentid(),employee.getJoblevelid(),new Date(),employee.getWorkstate());
        employee.setWorkstate("在职");
        if (employeeService.updateEmp(employee) + employeeremoveService.addEmploymove(employeeremove) == 2) {
            return RespBean.ok("调动成功!");
        }
        return RespBean.error("调动失败!");
    }


}
