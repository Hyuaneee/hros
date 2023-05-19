package com.haoqi.hros.service;

import com.haoqi.hros.mapper.PositionMapper;
import com.haoqi.hros.model.OpLog;
import com.haoqi.hros.model.Position;
import com.haoqi.hros.service.utils.Hruitls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Transactional
@Service
public class PositionService {
    @Autowired
    PositionMapper positionMapper;

    @Autowired
    OplogService oplogService;

    public List<Position> getAllPositions() {
        return positionMapper.getAllPositions();
    }

    public Integer addPosition(Position position) {
        // 设置默认值
        position.setEnabled(true);
        position.setCreatedate(new Date());
        oplogService.addOpLog(new OpLog((byte) 1,new Date(),"添加职位: " + position.getName(), Hruitls.getCurrent().getName()));
        return positionMapper.insertSelective(position);
    }

    public Integer updatePositions(Position position) {
        position.setCreatedate(new Date());
        oplogService.addOpLog(new OpLog((byte) 1,new Date(),"更新职位:"+ position.getName(), Hruitls.getCurrent().getName()));

        return positionMapper.updateByPrimaryKeySelective(position);
    }

    public Integer deletePositionById(Integer id) {
        oplogService.addOpLog(new OpLog((byte) 1,new Date(),"删除职位: id = " + id, Hruitls.getCurrent().getName()));
        Position position = new Position();
        position.setId(id);
        position.setStatus(1);
        return positionMapper.updateByPrimaryKeySelective(position);
    }

    public Integer deletePositionsByIds(Integer[] ids) {
        oplogService.addOpLog(new OpLog((byte) 1,new Date(),"批量删除职位: ids = " + ids.toString(), Hruitls.getCurrent().getName()));

        return positionMapper.deletePositionsByIds(ids);
    }

    public List<Position> serchPositions(Position position) {
        position.setName("%"+position.getName()+"%");
        return positionMapper.serchPositions(position);
    }
}
