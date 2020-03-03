package com.apimonitor.system.service.impl;


import com.apimonitor.system.entity.TbUser;
import com.apimonitor.system.mapper.TbUserMapper;
import com.apimonitor.system.service.TbUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
