package com.haoqi.hros.service;

import com.haoqi.hros.mapper.EmployeeMapper;
import com.haoqi.hros.model.*;
import com.haoqi.hros.model.datas.DataModel;
import com.haoqi.hros.model.datas.DataModelT;
import com.haoqi.hros.model.datas.DataModels;
import com.haoqi.hros.service.utils.EmailUtils;
import com.haoqi.hros.service.utils.Hruitls;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
@Transactional
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    OplogService oplogService;
    @Autowired
    EmployeeRecycleService employeeRecycleService;

    /*  运行的这个类时的日志打印*/
    public final static Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
    DecimalFormat decimalFormat = new DecimalFormat("##.00");



    public RespPageBean getEmploteeByPge(Integer page, Integer size, Employee employee, Date[] beginDateScope) {

        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Employee> data = employeeMapper.getEmployeeByPage(page, size, employee, beginDateScope);
        Long total = employeeMapper.getTotal(employee, beginDateScope);
        return new RespPageBean(total, data);
    }

    public RespPageBean getEmploteecByPge(Integer page, Integer size, Employee employee, Date[] beginDateScope) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Employee> data = employeeMapper.getEmployeecByPage(page, size, employee, beginDateScope);
        Long total = employeeMapper.getTotalc(employee, beginDateScope);
        return new RespPageBean(total, data);
    }

    public Integer addEmp(Employee employee) {
        Date beginContract = employee.getBegincontract();
        Date endContract = employee.getEndcontract();
        double month = (Double.parseDouble(yearFormat.format(endContract)) - Double.parseDouble(yearFormat.format(beginContract))) * 12 + (Double.parseDouble(monthFormat.format(endContract)) - Double.parseDouble(monthFormat.format(beginContract)));
        employee.setContractterm(Double.parseDouble(decimalFormat.format(month / 12)));
        int result = employeeMapper.insertSelective(employee);
        oplogService.addOpLog(new OpLog((byte) 2, new Date(), "员工入职::name:" + employee.getName() + "workId:" + employee.getWorkid(), Hruitls.getCurrent().getName()));
        EmailUtils.sendEmail(new EmailModel(employee, "人事管理系统测试##员工入职","emailpy.py"));
//        mailReceiver.handler(employee);
        return result;
    }


    public Integer deleteEmpByEid(Integer id) {
        Employee employee = employeeMapper.getEmployeeById(id);
        double month = (Double.parseDouble(yearFormat.format(new Date())) - Double.parseDouble(yearFormat.format(employee.getBegindate()))) * 12 + (Double.parseDouble(monthFormat.format(new Date())) - Double.parseDouble(monthFormat.format(employee.getBegindate())));
        employee.setNotworkdate(new Date());
        employee.setWorkage((int) Double.parseDouble(decimalFormat.format(month / 12)));
        employee.setWorkstate("离职");
        employee.setId(null);
        employeeRecycleService.addEmployeeRecycle(employee);
        oplogService.addOpLog(new OpLog((byte) 9, new Date(), "员工离职:name:" + employee.getName() + "---workId:" + employee.getWorkid(), Hruitls.getCurrent().getName()));
        EmailUtils.sendEmail(new EmailModel(employee, "人事管理系统测试##员工离职","emailpyout.py"));
        employee.setId(id);
        employee.setStatus(1);
        return employeeMapper.updateByPrimaryKeySelective(employee);
    }

    public Integer updateEmp(Employee employee) {
        oplogService.addOpLog(new OpLog((byte) 11, new Date(), "更新员工资料::name:" + employee.getName() + "---workId:" + employee.getWorkid(), Hruitls.getCurrent().getName()));
        return employeeMapper.updateByPrimaryKeySelective(employee);
    }


    public Integer maxWorkID() {
        return employeeMapper.maxWorkID();
    }

    public Integer addEmps(List<Employee> list) {
        oplogService.addOpLog(new OpLog((byte) 2, new Date(), "批量导入员工资料", Hruitls.getCurrent().getName()));
        return employeeMapper.addEmps(list);
    }

    public Integer deleteEmpByEids(Integer[] ids) {
        oplogService.addOpLog(new OpLog((byte) 9, new Date(), "员工批量离职:name:" , Hruitls.getCurrent().getName()));
        return employeeMapper.deleteByPrimaryKeys(ids);
    }

    public RespPageBean getEmployeeByPageWithSalary(Integer page, Integer size) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Employee> list = employeeMapper.getEmployeeByPageWithSalary(page, size);
        RespPageBean respPageBean = new RespPageBean();
        respPageBean.setData(list);
        respPageBean.setTotal(employeeMapper.getTotal(null, null));
        return respPageBean;
    }

    public Integer updateEmployeeSalaryById(Integer eid, Integer sid) {
        oplogService.addOpLog(new OpLog((byte) 7, new Date(), "员工套账变动:workId:" + eid + "套账编号:" + sid, Hruitls.getCurrent().getName()));
        return employeeMapper.updateEmployeeSalaryById(eid, sid);
    }

    public List<DataModel> dataViewPosition() {
        return employeeMapper.dataViewPosition();
    }

    public List<DataModel> dataViewJobLevel() {
        return employeeMapper.dataViewJobLevel();
    }

    public List<DataModels> dataViewJobLevelAge() {
        return employeeMapper.dataViewJobLevelAge();
    }


    public List<DataModel> dataViewDepartment() {
        return employeeMapper.dataViewDepartment();
    }

    public List<DataModel> dataViewNation() {
        return employeeMapper.dataViewNation();
    }

    public List<DataModel> dataViewPoliticsstatus() {
        return employeeMapper.DataViewPoliticsstatus();
    }

    public List<DataModel> dataViewtiptopdegree() {
        List<Employee> employeeList = employeeMapper.selectByPrimaryKey();
        Map<String, Integer> map = new HashMap<>();
        List<DataModel> dataModels = new ArrayList<>();
        employeeList.stream().forEach(o -> {
            if (!map.containsKey(o.getTiptopdegree())) {
                map.put(o.getTiptopdegree(), 1);
            } else {
                map.put(o.getTiptopdegree(), map.get(o.getTiptopdegree()) + 1);
            }
        });
        map.forEach((o1, o2) -> {
            dataModels.add(new DataModel(o1, o2));
        });
        return dataModels;
    }

    public List<DataModel> dataViewschool() {
        List<Employee> employeeList = employeeMapper.selectByPrimaryKey();
        Map<String, Integer> map = new HashMap<>();
        List<DataModel> dataModels = new ArrayList<>();
        employeeList.stream().forEach(o -> {
            if (!map.containsKey(o.getSchool())) {
                map.put(o.getSchool(), 1);
            } else {
                map.put(o.getSchool(), map.get(o.getSchool()) + 1);
            }
        });
        map.forEach((o1, o2) -> {
            dataModels.add(new DataModel(o1, o2));
        });
        return dataModels;
    }


    public List<DataModelT> DataViewDepartmentT() {
        List<DataModelT> dataModelTS = new ArrayList<>();
        dataViewDepartment().stream().forEach(o -> {
            dataModelTS.add(new DataModelT(o.getSum(), o.getName()));
        });
        List<EmployeeRecycle> employeeRecycleList = employeeRecycleService.getAllEmployeeRecycle();
        employeeRecycleService.dataViewDepartment().stream()
                .forEach(o -> {
                    dataModelTS.stream()
                            .filter(o1 -> o.getName().equals(o1.getName()))
                            .forEach(o1 -> {
                                o1.setDimission(o.getSum());
                                double d = (o.getSum() / new Double(employeeRecycleList.size()));
                                o1.setaDouble((double) Math.round(d * 100) / 100);
                            });

                });
        return dataModelTS;
    }

    public List<DataModelT> dataViewWorkAge() {
        List<DataModelT> dataModelTS = new ArrayList<>();
        dataViewPosition().stream().forEach(o -> {
            dataModelTS.add(new DataModelT(o.getSum(), o.getName()));
        });
        List<EmployeeRecycle> employeeRecycleList = employeeRecycleService.getAllEmployeeRecycle();
        employeeRecycleService.dataViewPosition().stream()
                .forEach(o -> {
                    dataModelTS.stream()
                            .filter(o1 -> o.getName().equals(o1.getName()))
                            .forEach(o1 -> {
                                o1.setDimission(o.getSum());
                                o1.setSum(o.getWorkage());
                                o1.setaDouble((double) Math.round((o.getSum() / new Double(employeeRecycleList.size())) * 100) / 100);

                            });

                });
        return dataModelTS;
    }

    public List<DataModelT> dataViewJobLevelTAge() {
        List<DataModelT> dataModelTS = new ArrayList<>();
        dataViewJobLevelAge().stream().forEach(o -> {
            dataModelTS.add(new DataModelT(o.getName(), o.getSum(), o.getWorkage()));
        });
        List<EmployeeRecycle> employeeRecycleList = employeeRecycleService.getAllEmployeeRecycle();
        employeeRecycleService.dataViewJobLevelT().stream()
                .forEach(o -> {
                    dataModelTS.stream()
                            .filter(o1 -> o.getName().equals(o1.getName()))
                            .forEach(o1 -> {
                                o1.setDimission(o.getSum());
                                double d = (o.getSum() / new Double(employeeRecycleList.size()));
                                o1.setSum(o.getWorkage());
                                o1.setaDouble((double) Math.round(d * 100) / 100);
                            });

                });
        return dataModelTS;
    }


    public RespPageBean getEmploteetByPge(Integer page, Integer size, Employee employee, Date[] beginDateScope) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Employee> data = employeeMapper.getEmployeetByPage(page, size, employee, beginDateScope);
        Long total = employeeMapper.getTotalt(employee, beginDateScope);
        return new RespPageBean(total, data);
    }

    public RespPageBean getAppraiseByPge(Integer page, Integer size, Employee employee, Date[] beginDateScope) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Employee> data = employeeMapper.getEmployeeAByPage(page, size, employee, beginDateScope);
        Long total = employeeMapper.getTotalA(employee, beginDateScope);
        return new RespPageBean(total, data);
    }

    public RespPageBean getEmploteeByPgeT(Integer page, Integer size, Employee employee, Date[] beginDateScope) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Employee> data = employeeMapper.getEmployeeByPageT(page, size, employee, beginDateScope);
        Long total = employeeMapper.getTotalT(employee, beginDateScope);
        return new RespPageBean(total, data);
    }

    public RespPageBean getEmploteeByPgeA(Integer page, Integer size, Employee employee, Date[] beginDateScope) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Employee> data = employeeMapper.getEmployeeByPageA(page, size, employee, beginDateScope);
        Long total = employeeMapper.getTotala(employee, beginDateScope);
        return new RespPageBean(total, data);
    }


    public RespPageBean getEmploteeByPgeM(Integer page, Integer size, Employee employee, Date[] beginDateScope) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Employee> data = employeeMapper.getEmployeeByPageM(page, size, employee, beginDateScope);
        Long total = employeeMapper.getTotalM(employee, beginDateScope);
        return new RespPageBean(total, data);
    }

    public RespPageBean getAllsalarts(Integer page, Integer size, Employee employee, Date[] beginDateScope) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Employee> data = employeeMapper.getEmployeeByPageS(page, size, employee, beginDateScope);
        Long total = employeeMapper.getTotaS(employee, beginDateScope);
        return new RespPageBean(total, data);
    }

    public int deleteEmpByName(String name) {
//        Employee employee = employeeMapper.getEmployeeById(id);
//        double month = (Double.parseDouble(yearFormat.format(new Date())) - Double.parseDouble(yearFormat.format(employee.getBegindate()))) * 12 + (Double.parseDouble(monthFormat.format(new Date())) - Double.parseDouble(monthFormat.format(employee.getBegindate())));
//        employee.setNotworkdate(new Date());
//        employee.setWorkage((int) Double.parseDouble(decimalFormat.format(month / 12)));
//        employee.setWorkstate("离职");
//        employee.setId(null);
//        employeeRecycleService.addEmployeeRecycle(employee);
//        oplogService.addOpLog(new OpLog((byte) 9, new Date(), "员工离职:name:" + employee.getName() + "---workId:" + employee.getWorkid(), Hruitls.getCurrent().getName()));
//        EmailUtils.sendEmail(new EmailModel(employee, "人事管理系统测试##员工离职","emailpyout.py"));
        return employeeMapper.deleteEmpByName(name);
    }
}

