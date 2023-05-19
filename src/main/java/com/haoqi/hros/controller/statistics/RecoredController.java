package com.haoqi.hros.controller.statistics;

import com.haoqi.hros.model.datas.DataModelT;
import com.haoqi.hros.service.DataModelTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/statistics/recored")
public class RecoredController {

    @Autowired
    DataModelTService dataModelTService;


    //部门入职统计，离职统计 离职率
    @Scheduled(cron = "0 0 3 * * ?")
    @GetMapping("/department")
    public List<DataModelT> dataViewDepartment() {
        return dataModelTService.dataViewDepartment();
    }


    //职位入职统计，离职统计 离职工龄 离职率
    @Scheduled(cron = "0 0 3 * * ?")
    @GetMapping("/workAge")
    public List<DataModelT> dataViewWorkAge() {
        return dataModelTService.dataViewWorkAge();
    }

    //职称入职统计，离职统计 离职工龄 离职率
    @GetMapping("/JobLevel")
    @Scheduled(cron = "0 0 3 * * ?")
    public List<DataModelT> DataViewJobLevelT() {
        return dataModelTService.dataViewJobLevelT();
    }

    @GetMapping("/Department")
    @Scheduled(cron = "0 0 3 * * ?")
    public List<DataModelT> DataViewDepartment() {
        return dataModelTService.dataViewDepartment();
    }


}
