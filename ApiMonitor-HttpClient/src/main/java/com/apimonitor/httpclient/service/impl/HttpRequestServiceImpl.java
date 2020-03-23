package com.apimonitor.httpclient.service.impl;

import com.apimonitor.httpclient.entity.HttpRequest;
import com.apimonitor.httpclient.entity.HttpRequestLog;
import com.apimonitor.httpclient.entity.HttpSequence;
import com.apimonitor.httpclient.http.client.HttpRequestHandle;
import com.apimonitor.httpclient.http.utils.HttpClientResult;
import com.apimonitor.httpclient.mapper.HttpRequestMapper;
import com.apimonitor.httpclient.service.HttpRequestLogService;
import com.apimonitor.httpclient.service.HttpRequestService;
import com.apimonitor.httpclient.service.HttpSequenceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * http请求表 服务实现类
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-19
 */
@Service
public class HttpRequestServiceImpl extends ServiceImpl<HttpRequestMapper, HttpRequest> implements HttpRequestService {

    @Autowired
    private HttpSequenceService httpSequenceService;

    @Autowired
    private HttpRequestService httpRequestService;

    @Autowired
    private HttpRequestLogService httpRequestLogService;

    @Override
    public void executeRequest(String jobId) {
        // jobId 查出sequest
        HttpSequence httpSequence = httpSequenceService.getOne(new QueryWrapper<HttpSequence>().eq("job_id",jobId));
        if(httpSequence==null){
            //删除任务
            return;
        }
        // jobId request
        List<HttpRequest> lists = httpRequestService.list(new QueryWrapper<HttpRequest>().eq("job_id",jobId));


        for(HttpRequest list:lists){
            HttpClientResult httpClientResult ;
            int start=(int)System.currentTimeMillis();
            HttpRequestLog httpRequestLog=new HttpRequestLog();
            //执行接口探测
            HttpRequestHandle httpRequestHandle = new HttpRequestHandle(list);
            try{
                httpClientResult=httpRequestHandle.execute();
            }catch (Exception e){
                return;
            }

            httpRequestLog.setJobId(list.getJobId());
            httpRequestLog.setRequestId(list.getId());
            if(httpClientResult.getCode() ==200){
                httpRequestLog.setStatus(1);
            }else{
                httpRequestLog.setStatus(0);
            }
            httpRequestLog.setCostTime((int)System.currentTimeMillis()-start);
            httpRequestLog.setStatusCode(""+httpClientResult.getCode());
            httpRequestLog.setResponseBody(httpClientResult.getContent());
            httpRequestLogService.save(httpRequestLog);
        }
    }
}
