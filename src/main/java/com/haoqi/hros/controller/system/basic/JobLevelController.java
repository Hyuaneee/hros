package com.haoqi.hros.controller.system.basic;


import com.haoqi.hros.model.JobLevel;
import com.haoqi.hros.model.Position;
import com.haoqi.hros.model.RespBean;
import com.haoqi.hros.service.JobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/system/basic/joblevel")
public class JobLevelController {

    @Autowired
    JobLevelService jobLevelService;

    @GetMapping("/")
    public List<JobLevel> getAllJobLevels() {
        return jobLevelService.getAllJobLevels();
    }
    @PostMapping("/serch/")
    public List<Position> serchJobLevel(@RequestBody JobLevel jobLevel){
        return  jobLevelService.serchJobLevel(jobLevel);

    }

    @PostMapping("/")
    public RespBean addJobLevel(@RequestBody JobLevel jobLevel) {
        if (jobLevelService.addJobLevel(jobLevel) == 1) {
            return RespBean.ok("添加成功!");
        } else {
            return RespBean.ok("添加失败!");
        }
    }


    @PutMapping("/")
    public RespBean updateJobLevelById(@RequestBody JobLevel jobLevel) {
        if (jobLevelService.updateJobLevelById(jobLevel) == 1) {
            return RespBean.ok("更新成功!");
        } else {
            return RespBean.ok("更新失败!");
        }
    }

    @DeleteMapping("/{id}")
    public RespBean deleteJobLevelById(@PathVariable Integer id){
        if (jobLevelService.deleteJobLevelById(id) == 1){
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    @DeleteMapping("/")
    public RespBean deleteJobLevelByIds(Integer[] ids){
        if (jobLevelService.deleteJobLevelByIds(ids) == ids.length){
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

}
