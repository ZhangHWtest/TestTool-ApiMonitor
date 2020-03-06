package com.apimonitor.system.controller;


import com.apimonitor.system.entity.Model.Findbody;
import com.apimonitor.system.entity.Model.UserPassword;
import com.apimonitor.system.entity.TbUser;
import com.apimonitor.system.entity.resultException.Result;
import com.apimonitor.system.entity.resultException.ResultCode;
import com.apimonitor.system.service.TbUserService;
import com.apimonitor.system.utils.FormatTime;
import com.apimonitor.system.utils.JwtUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 用户管理 前端控制器
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-03
 */

@RestController
@RequestMapping("/user")
public class TbUserController {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private TbUserService tbUserService;

    /**
     * 登录接口
     * @param loginUser
     * @return token
     */
    @PostMapping("/login")
    public Result logIn(@RequestBody TbUser loginUser, HttpServletRequest request) {


        //根据username查询TbUser
        TbUser tbUser=tbUserService.findByUsername(loginUser.getName());

        if (tbUser != null && encoder.matches(loginUser.getPassword(),tbUser.getPassword())){
            if(tbUser.getStatus() != 0){

                // 登录成功，构造token
                Map<String,Object> mapToken=new HashMap<>();
                String token=jwtUtils.createJwt(tbUser.getId().toString(),tbUser.getName(),mapToken);

                Map<String, Object> resultData = new LinkedHashMap<>();
                //数据库时间转换
                String str = FormatTime.formatTime(tbUser.getCreateTime());
                resultData.put("name", tbUser.getName());
                resultData.put("nickName", tbUser.getNickName());
                resultData.put("avatar", tbUser.getAvatar());
                resultData.put("email", tbUser.getEmail());
                resultData.put("mobile", tbUser.getMobile());
                resultData.put("createTime", str);
                resultData.put("token", token);
                //定义返回数据
                return  new Result(ResultCode.SUCCESS,resultData);

            }else {
                return new Result(ResultCode.DISABLDE);
            }
        } else {
            return new Result(ResultCode.MOBILEORPASSWORDERROR);

        }
    }

    /**
     * 用户列表
     */
    @PostMapping("/find")
    public Result findUser(@RequestBody Findbody findbody, HttpServletRequest request) {

        IPage<TbUser> page = new Page<>(findbody.getPageNum(), findbody.getPageSize());
        QueryWrapper<TbUser> userWP = new QueryWrapper<>();
        if(findbody.getStartTime() == null && findbody.getEndTime() != null){
            findbody.setStartTime("2020-01-01 00:00:00");
        }else if(findbody.getStartTime() != null && findbody.getEndTime() == null){
            findbody.setEndTime("2029-12-31 00:00:00");
        }else if(findbody.getStartTime() == null && findbody.getEndTime() == null){
            findbody.setStartTime("2020-01-01 00:00:00");
            findbody.setEndTime("2029-12-30 00:00:00");
        }

        List<TbUser> userRoles = tbUserService.page(page, userWP
                .and( wq-> wq.like("name", findbody.getMes())
                        .or().like("nick_name", findbody.getMes())
                        .or().eq("mobile", findbody.getMes())
                        .or().like("email", findbody.getMes()))
                .and(findbody.getStatus() != null,wq-> wq.eq("status",findbody.getStatus()))
                .and(wq-> wq.between("create_time",findbody.getStartTime(),findbody.getEndTime()))).getRecords();

        List<Object> users = new ArrayList<>();
        Map<String, Object> usersMap = new LinkedHashMap<>();
        for (TbUser seletUsers : userRoles) {
            Map<String, Object> userLists = new LinkedHashMap<>();
            userLists.put("id", seletUsers.getId());
            userLists.put("name", seletUsers.getName());
            userLists.put("nickName", seletUsers.getNickName());
            userLists.put("email", seletUsers.getEmail());
            userLists.put("mobile", seletUsers.getMobile());
            userLists.put("status", seletUsers.getStatus());
            userLists.put("createTime", FormatTime.formatTime(seletUsers.getCreateTime()));
            userLists.put("updateTime", FormatTime.formatTime(seletUsers.getLastUpdateTime()));
            userLists.put("lastUpdateBy",seletUsers.getLastUpdateBy());

            users.add(userLists);
        }
            usersMap.put("users", users);
            usersMap.put("total", page.getTotal());

            return  new Result(ResultCode.SUCCESS,usersMap);

        }

    /**
     * 修改用户
     */
    @PostMapping("/update")
    public Result updateUser(@RequestBody TbUser updateUser, HttpServletRequest request){
        //接口传过来是 updateUser，补全数据
        updateUser.setLastUpdateTime(LocalDateTime.now());
            if(tbUserService.updateById(updateUser)){
                return  new Result(ResultCode.SUCCESS);
            }else{
                return  new Result(ResultCode.FAIL);
            }

    }

    /**
     * 创建用户
     */
    @PostMapping("/create")
    public Result createUser(@RequestBody TbUser createUser, HttpServletRequest request){
        //接口传过来是 updateUser，补全数据
        createUser.setPassword(encoder.encode(createUser.getPassword()));
        createUser.setCreateTime(LocalDateTime.now());
        createUser.setCreateBy(createUser.getName());
        createUser.setLastUpdateTime(LocalDateTime.now());
        createUser.setLastUpdateBy(createUser.getName());
        if(tbUserService.save(createUser)){
            return  new Result(ResultCode.SUCCESS);
        }else{
            return  new Result(ResultCode.FAIL);
        }
    }


    @GetMapping("/getuserbyid/{id}")
    public Result getUserById(@PathVariable(name = "id") Long id, HttpServletRequest request){
            TbUser getUserById =tbUserService.getById(id);
            Map<String, Object> userLists = new LinkedHashMap<>();
            userLists.put("id",getUserById.getId());
            userLists.put("name",getUserById.getName());
            userLists.put("nickName",getUserById.getNickName());
            userLists.put("email",getUserById.getEmail());
            userLists.put("mobile",getUserById.getMobile());
            userLists.put("status",getUserById.getStatus().toString());

            return  new Result(ResultCode.SUCCESS,userLists);

    }

    @DeleteMapping("/deleteuser/{id}")
    public Result deleteUserById(@PathVariable(name = "id") Long id, HttpServletRequest request){

            if(tbUserService.removeById(id)){
                return  new Result(ResultCode.SUCCESS);
            }else{
                return  new Result(ResultCode.FAIL);
            }

    }

    @PostMapping("/updatepassword")
    public Result updatePassword(@RequestBody UserPassword userPassword,
                                          HttpServletRequest request){
            TbUser user=tbUserService.getOne(new QueryWrapper<TbUser>().eq("name", userPassword.getName()));
            if(encoder.matches(userPassword.getPassword(),user.getPassword())){
                user.setPassword(encoder.encode(userPassword.getNewPassword()));
                user.setLastUpdateBy(user.getName());
                user.setLastUpdateTime(LocalDateTime.now());

                tbUserService.updateById(user);
                request.getSession().removeAttribute(user.getName());
                return  new Result(ResultCode.SUCCESS);

            }else{
                return  new Result(ResultCode.FAIL);
            }

    }

}

