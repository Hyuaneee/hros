package com.haoqi.hros.service;

import com.haoqi.hros.mapper.OpLogMapper;
import com.haoqi.hros.model.OpLog;
import com.haoqi.hros.model.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OplogService {

    @Autowired
    OpLogMapper opLogMapper;

    public RespPageBean getAllsOplog(Integer page, Integer size, Integer logtype, Date[] beginDateScope ) {
        if (page != null && size != null){
            page = (page - 1) * size;
        }
        Long total = opLogMapper.selectByPrimaryKey( logtype,beginDateScope);
        return  new RespPageBean(total, opLogMapper.getAllsOplog(page, size, logtype,beginDateScope));
    }
    public void addOpLog(OpLog opLog){
        opLogMapper.insertSelective(opLog);

    }

    public List<OpLog> getAllsOplogm() {
        return opLogMapper.getAllsOplogm();
    }
}
