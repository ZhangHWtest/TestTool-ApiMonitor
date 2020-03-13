package com.apimonitor.httpclient.controller;


import com.apimonitor.httpclient.entity.JobInfo;
import com.apimonitor.httpclient.entity.model.JobAndRequestForm;
import com.apimonitor.httpclient.service.ApiRequestService;
import com.apimonitor.httpclient.service.JobInfoService;
import com.apimonitor.httpclient.utils.GuidGenerator;
import com.apimonitor.system.entity.resultException.Result;
import com.apimonitor.system.entity.resultException.ResultCode;
import com.github.pagehelper.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * http序列表 前端控制器
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-12
 */
@RestController
@RequestMapping("/job")
public class JobInfoController {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobInfoController.class);

    @Autowired
    private JobInfoService jobInfoService;

    @Autowired
    private ApiRequestService apiRequestService;


    @PostMapping("/list")
    public Result monitorList() {
        List<JobInfo> jobInfoList=jobInfoService.list();
        return new Result(ResultCode.SUCCESS,jobInfoList);
    }

    /**
     * 添加单个监控
     */
    @PostMapping("/saveSingle")
    public Result saveSingleMonitor(@RequestBody JobAndRequestForm form){
        form.setJobId(GuidGenerator.generate());
        if(jobInfoService.save(form.getJobInfo(form)) && apiRequestService.save(form.getApiRequest(form))){
            return new Result(ResultCode.SUCCESS);
        }else {
            return new Result(ResultCode.FAIL);
        }

//        try{
//            //添加
//            if(StringUtil.isEmpty(form.getGuid())){
//
//                HttpSequence httpSequence = HttpSequence.getHttpSequence(form);
//                httpSequence.setGuid(GuidGenerator.generate());
//
//                HttpRequest httpRequest = HttpRequest.getHttpRequest(form);
//                httpRequest.setGuid(GuidGenerator.generate());
//                httpRequest.setPguid(httpSequence.getGuid());
//
//                httpSequenceService.insertMyHttpSequest(httpSequence);
//                httpRequestService.insertHttpRequest(httpRequest);
//            }else{
//                //修改
//                HttpSequence httpSequence = HttpSequence.getHttpSequence(form);
//                HttpRequest httpRequest = HttpRequest.getHttpRequest(form);
//                httpSequenceService.update(httpSequence);
//                httpRequestService.updateHttpRequest(httpRequest);
//            }
//            return new Result(ResultCode.SUCCESS);
//        }catch(Exception e){
//            LOGGER.error("/saveSingle错误日志",e);
//            return new Result(ResultCode.FAIL);
//        }
    }


    /**
     * 启用停用监控按钮
     */
    @GetMapping("/enabled")
    public Result  enableMonitor(@RequestParam(name = "jobId") String jobId,
                                 @RequestParam(name = "enabled") String enabled){
       try {
           jobInfoService.enableMonitor(jobId);
           return new Result(ResultCode.SUCCESS);
       }catch (Exception e){
           LOGGER.error("/enabled",e);
           return new Result(ResultCode.FAIL);
       }


    }


}

