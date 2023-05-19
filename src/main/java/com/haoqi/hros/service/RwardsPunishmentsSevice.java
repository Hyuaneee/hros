package com.haoqi.hros.service;

import com.haoqi.hros.mapper.RwardsPunishmentsMapper;
import com.haoqi.hros.model.OpLog;
import com.haoqi.hros.model.RwardsPunishments;
import com.haoqi.hros.service.utils.Hruitls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Transactional
@Service
public class RwardsPunishmentsSevice {

    @Autowired
    RwardsPunishmentsMapper rwardsPunishmentsMapper;
    @Autowired
    OplogService oplogService;

    public List<RwardsPunishments> getAllRwardsPunishments() {
        return rwardsPunishmentsMapper.getAllRwardsPunishments();
    }

    public Integer updateRwardsPunishments(RwardsPunishments rwardsPunishments) {
        oplogService.addOpLog(new OpLog((byte) 1,new Date(),"更新奖惩: " + rwardsPunishments.getRpmsg(), Hruitls.getCurrent().getName()));
        return rwardsPunishmentsMapper.updateByPrimaryKeySelective(rwardsPunishments);
    }

    public Integer addRwardsPunishments(RwardsPunishments rwardsPunishments) {
        oplogService.addOpLog(new OpLog((byte) 1,new Date(),"添加奖惩: " + rwardsPunishments.getRpmsg(), Hruitls.getCurrent().getName()));
        return rwardsPunishmentsMapper.insertSelective(rwardsPunishments);
    }

    public Integer deleteRwardsPunishmentsById(Integer id) {
        oplogService.addOpLog(new OpLog((byte) 1,new Date(),"删除奖惩: id = " + id, Hruitls.getCurrent().getName()));

        return rwardsPunishmentsMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteRwardsPunishmentsByIds(Integer[] ids) {
        oplogService.addOpLog(new OpLog((byte) 1,new Date(),"批量删除奖惩: ids = " + ids.toString(), Hruitls.getCurrent().getName()));

        return rwardsPunishmentsMapper.deleteRwardsPunishmentsByIds(ids);
    }
    public List<RwardsPunishments> sercRwardsPunishments(RwardsPunishments rwardsPunishments) {
        rwardsPunishments.setRpmsg("%" + rwardsPunishments.getRpmsg() + "%");
        return rwardsPunishmentsMapper.sercRwardsPunishments(rwardsPunishments);
    }
}
