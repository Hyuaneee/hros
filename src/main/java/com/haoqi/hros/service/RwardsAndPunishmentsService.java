package com.haoqi.hros.service;

import com.haoqi.hros.mapper.RwardsAndPunishmentsMapper;
import com.haoqi.hros.model.RwardsAndPunishments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RwardsAndPunishmentsService {

    @Autowired
    RwardsAndPunishmentsMapper rwardsAndPunishmentsMapper;

    public List<RwardsAndPunishments> getAllRwardsAndPunishments() {
        return  rwardsAndPunishmentsMapper.getAllRwardsAndPunishments();
    }
}
