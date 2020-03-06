package com.apimonitor.system.controller;


import com.apimonitor.system.entity.TbMenu;
import com.apimonitor.system.entity.TbRoleMenu;
import com.apimonitor.system.entity.TbUser;
import com.apimonitor.system.entity.TbUserRole;
import com.apimonitor.system.entity.resultException.Result;
import com.apimonitor.system.entity.resultException.ResultCode;
import com.apimonitor.system.service.TbMenuService;
import com.apimonitor.system.service.TbRoleMenuService;
import com.apimonitor.system.service.TbUserRoleService;
import com.apimonitor.system.service.TbUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Long.valueOf;

/**
 * <p>
 * 菜单管理 前端控制器
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-03
 */

@RestController
@RequestMapping("/menu")
public class TbMenuController {

    @Autowired
    private TbUserRoleService tbUserRoleService;

    @Autowired
    private TbRoleMenuService TbRoleMenuService;

    @Autowired
    private TbMenuService tbMenuService;

    @PostMapping("/getmenu")
    public Result getUserMenu( HttpServletRequest request) {
        List<Object> resultData = new ArrayList<>();
            /*
             * 第一步：解析token中的claims,获取用户 id
             * 第二步：user_role 中根据user_id查出role_id
             * 第三步：role_menu 中根据role_id查出menu_id
             * 第四步：menu 中根据id 查出name
             * 第五步：根据menu中parent_id 查出二级菜单
             */
        // 第一步：解析token中的claims,获取用户ID
        Claims claims=(Claims) request.getAttribute("user_claims");
        // 第二步：user_role 中根据user_id查出role_id
        List<TbUserRole> userRoles = tbUserRoleService.findRoleByUserId(valueOf(claims.getId()));
            if (!userRoles.isEmpty()) {
                // 第三步：role_menu 中根据role_id查出menu_id
                for (TbUserRole userRole : userRoles) {
                    List<TbRoleMenu> roleMenus = TbRoleMenuService.findMenuidByRoleid(userRole.getRoleId());
                        for (TbRoleMenu roleMenu : roleMenus) {
                            QueryWrapper<TbMenu> meniuQW = new QueryWrapper<>();
                            //获取一级菜单
                            List<TbMenu> menus = tbMenuService.list(meniuQW
                                    .eq("parent_id",0)
                                    .eq("id", roleMenu.getMenuId())
                                    .orderByAsc("order_num"));

                            for (TbMenu menusList: menus) {
                                Map<String,Object> menuLists=new LinkedHashMap<>();
                                //获取二级菜单
                                QueryWrapper<TbMenu> childMenuWQ = new QueryWrapper<>();
                                List<TbMenu> childMenu = tbMenuService
                                        .list(childMenuWQ
                                        .eq("parent_id", menusList.getId())
                                        .eq("type",1)
                                        .orderByAsc("order_num"));

                                List<Object> childMenusList=new ArrayList<>();
                                if(!childMenu.isEmpty()){
                                    for(TbMenu childMenus:childMenu){
                                        Map<String,Object> childMenusMap=new LinkedHashMap<>();
                                        childMenusMap.put("id",childMenus.getId());
                                        childMenusMap.put("name",childMenus.getName());
                                        childMenusMap.put("url",childMenus.getUrl());
                                        childMenusMap.put("icon",childMenus.getIcon());
                                        childMenusMap.put("parent_id",childMenus.getParentId());
                                        childMenusList.add(childMenusMap);

                                        menuLists.put("childMenu", childMenusList);
                                    }
                                }
                                menuLists.put("id", menusList.getId());
                                menuLists.put("name", menusList.getName());
                                menuLists.put("url", menusList.getUrl());
                                menuLists.put("icon", menusList.getIcon());
                                menuLists.put("order_num",menusList.getOrderNum());
                                resultData.add(menuLists);
                            }
                        }
                    }
                    //返回数据
                    return  new Result(ResultCode.SUCCESS,resultData);

                } else {
                    return new Result(ResultCode.UNAUTHORISE);
                }


    }

}

