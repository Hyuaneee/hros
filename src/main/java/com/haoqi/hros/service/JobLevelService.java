package com.haoqi.hros.service;

import com.haoqi.hros.model.JobLevel;
import com.haoqi.hros.model.OpLog;
import com.haoqi.hros.model.Position;
import com.haoqi.hros.service.utils.Hruitls;
import com.haoqi.hros.mapper.JobLevelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Transactional
@Service
public class JobLevelService implements Serializable {

    @Autowired
    JobLevelMapper jobLevelMapper;
    @Autowired
    OplogService oplogService;


    public List<JobLevel> getAllJobLevels() {
        return  jobLevelMapper.getAllJobLevels();
    }

    public Integer addJobLevel(JobLevel jobLevel) {
        jobLevel.setCreatedate(new Date());
        jobLevel.setEnabled(true);
        oplogService.addOpLog(new OpLog((byte) 1,new Date(),"添加职称" + jobLevel.getName(), Hruitls.getCurrent().getName()));

        return jobLevelMapper.insertSelective(jobLevel);
    }

    public Integer updateJobLevelById(JobLevel jobLevel) {
        oplogService.addOpLog(new OpLog((byte) 1,new Date(),"更新职称" + jobLevel.getName(), Hruitls.getCurrent().getName()));

        return jobLevelMapper.updateByPrimaryKeySelective(jobLevel);
    }

    public Integer deleteJobLevelById(Integer id) {
        oplogService.addOpLog(new OpLog((byte) 1,new Date(),"删除职称 : id=" + id, Hruitls.getCurrent().getName()));
        JobLevel jobLevel = new JobLevel();
        jobLevel.setId(id);
        jobLevel.setStatus(1);
        return jobLevelMapper.updateByPrimaryKeySelective(jobLevel);
    }

    public Integer deleteJobLevelByIds(Integer[] ids) {
        oplogService.addOpLog(new OpLog((byte) 1,new Date(),"批量删除职称: ids=" + ids.toString(), Hruitls.getCurrent().getName()));

        return jobLevelMapper.deleteJobLevelByIds(ids);
    }

    public List<Position> serchJobLevel(JobLevel jobLevel) {
        jobLevel.setName("%"+jobLevel.getName()+"%");
        return jobLevelMapper.serchJobLeve( jobLevel);
    }
}
