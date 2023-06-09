package com.haoqi.hros.mapper;

import com.haoqi.hros.model.JobLevel;
import com.haoqi.hros.model.Position;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobLevelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JobLevel record);

    int insertSelective(JobLevel record);

    JobLevel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JobLevel record);

    int updateByPrimaryKey(JobLevel record);

    List<JobLevel> getAllJobLevels( );

    Integer deleteJobLevelByIds(@Param("ids")Integer[] ids);

    List<Position> serchJobLeve(JobLevel jobLevel);

}