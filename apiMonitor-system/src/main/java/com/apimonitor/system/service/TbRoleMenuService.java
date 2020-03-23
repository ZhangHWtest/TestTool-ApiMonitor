package com.apimonitor.system.service;


import com.apimonitor.system.entity.TbRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色菜单 服务类
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-03
 */
public interface TbRoleMenuService extends IService<TbRoleMenu> {
    List<TbRoleMenu> findMenuidByRoleid(Long role_id);

}
