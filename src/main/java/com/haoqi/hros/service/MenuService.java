package com.haoqi.hros.service;

import com.haoqi.hros.mapper.MenuMapper;
import com.haoqi.hros.mapper.MenuRoleMapper;
import com.haoqi.hros.model.Menu;
import com.haoqi.hros.model.OpLog;
import com.haoqi.hros.service.utils.Hruitls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Transactional
@Service
public class MenuService {
    @Autowired
    MenuMapper menuMapper;

    @Autowired
    MenuRoleMapper menuRoleMapper;

    @Autowired
    OplogService oplogService;

    public List<Menu> getMenusById() {
        return menuMapper.getMenusByHrId(Hruitls.getCurrent().getId());
    }


    // @Cacheable
    public List<Menu> getAllMenusWithRole() {
        return menuMapper.getAllMenusWithRole();
    }



    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }


    public List<Integer> getMidsByRid(Integer rid) {

        return menuRoleMapper.getMidsByRid(rid);

    }


    @Transactional
    public boolean updateMenuRole(Integer rid, Integer[] mids) {

        oplogService.addOpLog(new OpLog((byte) 1, new Date(), "角色权限变更", Hruitls.getCurrent().getName()));

        menuRoleMapper.deleteByRid(rid);
        if (mids != null && mids.length > 0) {
            Integer result = menuRoleMapper.insertRecord(rid, mids);
            return result == mids.length;
        } else {
            return true;
        }
    }
}
