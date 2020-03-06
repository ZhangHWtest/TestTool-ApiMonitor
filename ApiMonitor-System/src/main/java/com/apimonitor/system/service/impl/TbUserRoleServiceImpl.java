package com.apimonitor.system.service.impl;


import com.apimonitor.system.entity.TbUserRole;
import com.apimonitor.system.mapper.TbUserRoleMapper;
import com.apimonitor.system.service.TbUserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户角色 服务实现类
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-03
 */
@Service
public class TbUserRoleServiceImpl extends ServiceImpl<TbUserRoleMapper, TbUserRole> implements TbUserRoleService {
    @Autowired
    private TbUserRoleService tbUserRoleService;

    @Override
    public List<TbUserRole> findRoleByUserId(Long id) {
        QueryWrapper<TbUserRole> userRoleQW = new QueryWrapper<>();
        return tbUserRoleService.list(userRoleQW.select("role_id").eq("user_id",  id));

    }
}
