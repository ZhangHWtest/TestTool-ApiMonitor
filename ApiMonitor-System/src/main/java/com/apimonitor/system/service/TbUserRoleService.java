package com.apimonitor.system.service;


import com.apimonitor.system.entity.TbUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户角色 服务类
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-03
 */
public interface TbUserRoleService extends IService<TbUserRole> {
    List<TbUserRole> findRoleByUserId(Long id);

}
