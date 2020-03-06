package com.apimonitor.system.service.impl;


import com.apimonitor.system.entity.TbRoleMenu;
import com.apimonitor.system.mapper.TbRoleMenuMapper;
import com.apimonitor.system.service.TbRoleMenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色菜单 服务实现类
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-03
 */
@Service
public class TbRoleMenuServiceImpl extends ServiceImpl<TbRoleMenuMapper, TbRoleMenu> implements TbRoleMenuService {

    @Autowired
    TbRoleMenuService tbRoleMenuService;
    @Override
    public List<TbRoleMenu> findMenuidByRoleid(Long role_id) {
        QueryWrapper<TbRoleMenu> userRoleMeniuQW = new QueryWrapper<>();
        return tbRoleMenuService.list(userRoleMeniuQW.in("role_id", role_id));
    }
}
