package com.apimonitor.system.service.impl;


import com.apimonitor.system.entity.TbUser;
import com.apimonitor.system.mapper.TbUserMapper;
import com.apimonitor.system.service.TbUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户管理 服务实现类
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-03
 */
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements TbUserService {


    @Autowired
    private  TbUserService tbUserService;

    @Override
    public TbUser findByUsername(String username) {
        //查询name等于前端传进来的用户名的数据，getOne 只支持数据唯一的查询
        return tbUserService.getOne(new QueryWrapper<TbUser>().eq("name", username));
    }
}
