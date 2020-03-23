package com.apimonitor.httpclient.controller;

import com.apimonitor.httpclient.entity.HttpRequest;
import com.apimonitor.httpclient.entity.HttpSequence;
import com.apimonitor.httpclient.entity.model.MonitorForm;
import com.apimonitor.httpclient.service.HttpRequestService;
import com.apimonitor.httpclient.service.HttpSequenceService;
import com.apimonitor.httpclient.service.HttpSystemService;
import com.apimonitor.httpclient.utils.GuidGenerator;
import com.apimonitor.system.entity.resultException.Result;
import com.apimonitor.system.entity.resultException.ResultCode;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/monitor")
public class ApiMonitorController {

    @Autowired
    private HttpSystemService httpSystemService;

    @Autowired
    private HttpSequenceService httpSequenceService;

    @Autowired
    private HttpRequestService httpRequestService;

    /**
     * 获取所有系统
     * @return
     */
    @GetMapping("/getSystem")
    public Result getAll(){
        return new Result(ResultCode.SUCCESS, httpSystemService.list());
    }

    /**
     * 获取监控列表+搜索
     */

    @PostMapping("/getMonitorList")
    public Result getMonitorList(@RequestBody MonitorForm monitorForm){

        return new Result(ResultCode.FAIL);
    }
    /**
     * 添加单个监控
     * @param monitorForm
     * @return
     */
    @PostMapping("/addSingleMonitor")
    public Result addSingleMonitor(@RequestBody MonitorForm monitorForm){

        try{
            // 先创建 jobId
            monitorForm.setCreateTime(LocalDateTime.now());
            monitorForm.setJobId(GuidGenerator.generate());
            HttpSequence httpSequence=monitorForm.getHttpSequence(monitorForm);
            if(httpSequenceService.save(httpSequence)){
                HttpRequest httpRequest=monitorForm.getHttpRequest(monitorForm);
                if(httpRequestService.save(httpRequest)){
                    return new Result(ResultCode.SUCCESS);
                }else {
                    return new Result(ResultCode.FAIL);
                }
            }else{
                return new Result(ResultCode.FAIL);
            }

        }catch(Exception e){
            return new Result(ResultCode.FAIL);
        }
    }


    @GetMapping("/enabledMonitor")
    public Result enabledMonitor(@RequestParam(name = "jobId") String jobId,@RequestParam(name = "enabled") int enabled){
        HttpSequence httpSequence=httpSequenceService.getOne(new QueryWrapper<HttpSequence>().eq("job_id",jobId));
        if(enabled ==1 && httpSequence.getEnabled()==0){
            // 传过来的参数时1:启动，检查数据库状态是0 未启用
            httpSequenceService.enableMonitor(jobId);
            return new Result(ResultCode.SUCCESS);
        }else if(enabled ==0 && httpSequence.getEnabled()==1){
            httpSequenceService.disableMonitor(jobId);
            return new Result(ResultCode.SUCCESS);
        }
        return new Result(ResultCode.FAIL);

    }



}
