package com.apimonitor.common.controller;


import com.apimonitor.common.entity.HttpSequence;
import com.apimonitor.common.service.HttpSequenceService;
import com.apimonitor.system.entity.resultException.Result;
import com.apimonitor.system.entity.resultException.ResultCode;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/monitor")
public class HttpSequenceController {
    @Autowired
    private HttpSequenceService httpSequenceService;

    @PostMapping("/list")
    public Result monitorList() {
        QueryWrapper<HttpSequence> httpSystemQW = new QueryWrapper<>();
        List<HttpSequence> httpSystemList= httpSequenceService.list(httpSystemQW.orderByDesc("id"));
        return   new Result(ResultCode.SUCCESS,httpSystemList);

    }

}

