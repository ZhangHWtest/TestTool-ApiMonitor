package com.apimonitor.common.controller;


import com.apimonitor.common.entity.HttpRequest;
import com.apimonitor.common.entity.HttpRequestForm;
import com.apimonitor.common.entity.HttpSystem;
import com.apimonitor.common.service.HttpRequestService;
import com.apimonitor.common.service.HttpSequenceService;
import com.apimonitor.common.service.HttpSystemService;
import com.apimonitor.system.entity.resultException.Result;
import com.apimonitor.system.entity.resultException.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/monitor")
public class MonitorController {
    protected static final Logger LOGGER = LoggerFactory.getLogger(MonitorController.class);

    @Autowired
    private HttpRequestService httpRequestService;

    @Autowired
    private HttpSequenceService httpSequenceService;


    @Autowired
    private HttpSystemService httpSystemService;

    /**
     * 查询List
     */
    @PostMapping("/getList")
    public Result getMonitorList() {
        httpSequenceService.list();

        if(! httpSequenceService.list().isEmpty()){
            return new Result(ResultCode.SUCCESS,httpSequenceService.list());
        }
        return new Result(ResultCode.FAIL);
    }

    /**
     * 添加单个api
     */
    @PostMapping("/createSingle")
    public Result createSingleMonitor(@RequestBody HttpRequestForm form){
        try{
           httpSystemService.save(form.getHttpSystem(form));
           httpSequenceService.save(form.getHttpSequence(form));
           httpRequestService.save(form.getHttpRequest(form));
           return new Result(ResultCode.SUCCESS);
        }catch(Exception e){
            LOGGER.error("错误日志",e);
            return new Result(ResultCode.FAIL);
        }
    }

    /**
     * 添加单监视器
     * @param map
     * @return
     */
    @PostMapping("/addSingleMonitor")
    public Result addSingleMonitor(ModelMap map) {
        HttpRequestForm form = new HttpRequestForm();
        map.put("form", form);
        return  new Result(ResultCode.SUCCESS);
    }

    /**
     * 添加群组
     */
    @PostMapping("/addGroup")
    public Result addGroup(@RequestBody HttpSystem systemName){
        if(httpSystemService.save(systemName)) {
            return new Result(ResultCode.SUCCESS);
        }
        return new Result(ResultCode.FAIL);
    }
    /**
     * 获取群组
     */
    @GetMapping("/getGroups")
    public Result getGroup(){
        List<HttpSystem> list =httpSystemService.list();

        return new Result(ResultCode.SUCCESS,list);
    }


}
