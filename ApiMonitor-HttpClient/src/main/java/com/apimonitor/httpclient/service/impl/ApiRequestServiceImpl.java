package com.apimonitor.httpclient.service.impl;

import com.apimonitor.httpclient.entity.ApiRequest;
import com.apimonitor.httpclient.entity.ApiRequestLog;
import com.apimonitor.httpclient.http.client.ApiRequestHandle;
import com.apimonitor.httpclient.mapper.ApiRequestMapper;
import com.apimonitor.httpclient.quartz.DynamicJobManager;
import com.apimonitor.httpclient.service.ApiRequestLogService;
import com.apimonitor.httpclient.service.ApiRequestService;
import com.apimonitor.httpclient.service.JobInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * http请求表 服务实现类
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-12
 */
@Service
public class ApiRequestServiceImpl extends ServiceImpl<ApiRequestMapper, ApiRequest> implements ApiRequestService {

    @Autowired
    private JobInfoService jobInfoService;

    @Autowired
    private ApiRequestService apiRequestService;

   @Autowired
   private ApiRequestLogService apiRequestLogService;




   //

    @Transactional
    @Override
    public void executeRequest(String guid) {
        List<ApiRequest> apiRequestLists=apiRequestService.list(new QueryWrapper<ApiRequest>().eq("job_id", guid));
        if(apiRequestLists == null){
            //删除任务
            return;
        }
        for(ApiRequest apiRequestList:apiRequestLists){
            ApiRequestHandle apiRequestHandle = new ApiRequestHandle(apiRequestList);
            apiRequestHandle.execute();
            apiRequestLogService.save(apiRequestHandle.apiRequestLog);
            //保存日志
            for(ApiRequestLog apiRequestLog : apiRequestHandle.apiRequestLogList){
                apiRequestLog.setJobId(apiRequestHandle.apiRequestLog.getJobId());
                apiRequestLogService.save(apiRequestLog);
            }
        }

    }
}
