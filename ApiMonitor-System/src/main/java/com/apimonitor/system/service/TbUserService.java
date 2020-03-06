package com.apimonitor.system.service;


import com.apimonitor.system.entity.TbUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户管理 服务类
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-03
 */
public interface TbUserService extends IService<TbUser> {

    /**
     * 根据username查询用户
     */

    TbUser findByUsername(String username);

}
