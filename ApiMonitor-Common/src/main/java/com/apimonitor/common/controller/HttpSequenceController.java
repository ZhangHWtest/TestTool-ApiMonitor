package com.apimonitor.common.controller;


import com.apimonitor.common.entity.HttpSequence;
import com.apimonitor.common.entity.HttpSystem;
import com.apimonitor.common.entity.Model.HttpSequenceForm;
import com.apimonitor.common.service.HttpRequestService;
import com.apimonitor.common.service.HttpSequenceService;
import com.apimonitor.common.service.HttpSystemService;
import com.apimonitor.system.entity.resultException.Result;
import com.apimonitor.system.entity.resultException.ResultCode;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.jws.Oneway;
import java.util.List;

/**
 * <p>
 * http序列表 前端控制器
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-03
 */
@RestController
@RequestMapping("/sequence")
public class HttpSequenceController {
    @Autowired
    private HttpSequenceService httpSequenceService;

    @Autowired
    private HttpSystemService httpSystemService;

    @Autowired
    private HttpRequestService httpRequestService;

    /**
     * 获取所有接口，根据条件查询
     */
    @PostMapping("/list")
    public Result monitorList() {
        QueryWrapper<HttpSequence> httpSystemQW = new QueryWrapper<>();
        List<HttpSequence> httpSystemList= httpSequenceService.list(httpSystemQW.orderByDesc("id"));
        return new Result(ResultCode.SUCCESS,httpSystemList);

    }

    /**
     * 添加单个监控接口
     * 步骤：
     *      1、创建系统http_system
     *      2、创建任务http_sequence
     *      3、创建需要监控的api数据http_request
     *      4、创建脚本job
     * 自定义参数 HttpSequenceForm 系统，任务，监控api组合，并且返回三组数据
     *
     */
    @PostMapping("/addAlone")
    public Result addAlone(@RequestBody HttpSequenceForm httpRequestForm) {

//       httpSequenceService.save(httpRequestForm.getHttpSequence(httpRequestForm));
//       httpRequestService.save(httpRequestForm.getHttpRequest(httpRequestForm));
       if(httpSystemService.save(httpRequestForm.getHttpSystem(httpRequestForm))){
           return new Result(ResultCode.SUCCESS);
       }
        return new Result(ResultCode.FAIL);
    }

}

