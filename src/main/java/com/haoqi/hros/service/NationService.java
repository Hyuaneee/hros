package com.haoqi.hros.service;

import com.haoqi.hros.mapper.NationMapper;
import com.haoqi.hros.model.Nation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NationService {
    @Autowired
    NationMapper nationMapper;
    public List<Nation> getAllNations() {
        return nationMapper.getAllNations();
    }
}
