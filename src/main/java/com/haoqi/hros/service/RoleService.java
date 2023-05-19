package com.haoqi.hros.service;


import com.haoqi.hros.mapper.RoleMapper;
import com.haoqi.hros.model.OpLog;
import com.haoqi.hros.model.Role;
import com.haoqi.hros.service.utils.Hruitls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Transactional
@Service
public class RoleService {
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    OplogService oplogService;


    public List<Role> getAllRoles() {
        return roleMapper.getAllRoles();
    }


    public Integer addRole(Role role) {
        if (!role.getName().startsWith("ROLE_")) {
            role.setName("ROLE_" + role.getName());
        }
        oplogService.addOpLog(new OpLog((byte) 1,new Date(),"添加角色: " + role.getName(), Hruitls.getCurrent().getName()));
        return roleMapper.insert(role);
    }



    public Integer deleteRoleById(Integer rid) {
        oplogService.addOpLog(new OpLog((byte) 1,new Date(),"删除奖惩: id = " + rid, Hruitls.getCurrent().getName()));

        return roleMapper.deleteByPrimaryKey(rid);
    }
}
