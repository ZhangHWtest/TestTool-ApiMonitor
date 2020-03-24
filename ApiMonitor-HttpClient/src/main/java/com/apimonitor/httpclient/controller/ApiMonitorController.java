package com.apimonitor.httpclient.controller;

import com.apimonitor.httpclient.entity.HttpRequest;
import com.apimonitor.httpclient.entity.HttpSequence;
import com.apimonitor.httpclient.entity.HttpSystem;
import com.apimonitor.httpclient.entity.model.FindMonitorForm;
import com.apimonitor.httpclient.entity.model.MonitorForm;
import com.apimonitor.httpclient.mapper.HttpSystemMapper;
import com.apimonitor.httpclient.quartz.DynamicJobManager;
import com.apimonitor.httpclient.service.HttpRequestService;
import com.apimonitor.httpclient.service.HttpSequenceService;
import com.apimonitor.httpclient.service.HttpSystemService;
import com.apimonitor.httpclient.utils.GuidGenerator;
import com.apimonitor.system.entity.TbUser;
import com.apimonitor.system.entity.resultException.Result;
import com.apimonitor.system.entity.resultException.ResultCode;
import com.apimonitor.system.utils.FormatTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/monitor")
public class ApiMonitorController {

    @Autowired
    private HttpSystemService httpSystemService;

    @Autowired
    private HttpSequenceService httpSequenceService;

    @Autowired
    private HttpRequestService httpRequestService;

    @Autowired
    private HttpSystemMapper httpSystemMapper;

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
    public Result getMonitorList(@RequestBody FindMonitorForm findMonitorForm){

            IPage<HttpSequence> page = new Page<>(findMonitorForm.getPageNum(), findMonitorForm.getPageSize());
            QueryWrapper<HttpSequence> monitorWP = new QueryWrapper<>();

            List<HttpSequence> monitorLists = httpSequenceService.page(page, monitorWP
                    .and(!findMonitorForm.getMonitorName().isEmpty(),wq-> wq.eq("job_name",findMonitorForm.getMonitorName()))
                    .orderByDesc("id"))
                    .getRecords();
            if(monitorLists != null){
                Map<String, Object> monitorMap = new LinkedHashMap<>();

                List<Object> monitorList=new ArrayList<>();
                for(HttpSequence httpSequence:monitorLists){

                    Map<String, Object> httpSequenceList = new LinkedHashMap<>();
                    httpSequenceList.put("id", httpSequence.getId());
                    httpSequenceList.put("jobId", httpSequence.getJobId());
                    httpSequenceList.put("jobName", httpSequence.getJobName());
                    HttpSystem httpSystem=httpSystemService.getById(httpSequence.getHttpSystemId());
                    httpSequenceList.put("httpSystem", httpSystem.getSystemName());
                    httpSequenceList.put("httpName", httpSequence.getHttpName());
                    httpSequenceList.put("remark", httpSequence.getRemark());
                    httpSequenceList.put("enabled", httpSequence.getEnabled());
                    httpSequenceList.put("jobCron", httpSequence.getJobCron());
                    httpSequenceList.put("type", httpSequence.getType());
                    httpSequenceList.put("createTime", FormatTime.formatTime(httpSequence.getCreateTime()));

                    monitorList.add(httpSequenceList);

                }
                monitorMap.put("monitor", monitorList);
                monitorMap.put("total", page.getTotal());
                return new Result(ResultCode.SUCCESS,monitorMap);
            }

        return new Result(ResultCode.FAIL);
    }
    /**
     * 添加单个监控
     * @param monitorForm
     * @return
     */
    @PostMapping("/addSingleMonitor")
    public Result addSingleMonitor(@RequestBody MonitorForm monitorForm){
        // 先创建 jobId
        monitorForm.setCreateTime(LocalDateTime.now());
        monitorForm.setJobId(GuidGenerator.generate());
        HttpSequence httpSequence=monitorForm.getHttpSequence(monitorForm);
        HttpSystem httpSystem=httpSystemService.getOne(new QueryWrapper<HttpSystem>().eq("system_name",monitorForm.getSystemName()));
        if(httpSystem != null){
            httpSequence.setHttpSystemId(httpSystem.getId());
            try{
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
        }else {
            HttpSystem newHttpSystem=new HttpSystem();
            newHttpSystem.setSystemName(monitorForm.getSystemName());
            newHttpSystem.setCreateTime(LocalDateTime.now());
            if(httpSystemService.save(newHttpSystem)){
                HttpSystem newHttpSystemT=httpSystemService.getOne(new QueryWrapper<HttpSystem>().eq("system_name",newHttpSystem.getSystemName()));
                httpSequence.setHttpSystemId(newHttpSystemT.getId());
                try{
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

        }
        return new Result(ResultCode.FAIL);
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

    @GetMapping("/deleteMonitor")
    public Result  deleteMonitor(@RequestParam(name = "jobId") String jobId){
        HttpSequence httpSequence=httpSequenceService.getOne(new QueryWrapper<HttpSequence>().eq("job_id",jobId));
        httpSequenceService.removeById(httpSequence.getId());
        DynamicJobManager dynamicJobManager = new DynamicJobManager(httpSequence);
        dynamicJobManager.delete();
        return new Result(ResultCode.SUCCESS);
    }


}
