package com.apimonitor.httpclient.http.client;

import com.apimonitor.httpclient.entity.ApiRequest;
import com.apimonitor.httpclient.entity.ApiRequestLog;
import com.apimonitor.httpclient.entity.JobInfo;
import com.github.pagehelper.StringUtil;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class JobInfoHandle {

    protected static final Logger LOGGER = LoggerFactory.getLogger(JobInfoHandle.class);


    private JobInfo jobInfo;

    public JobInfoHandle(JobInfo jobInfo) {
        this.jobInfo = jobInfo;
    }

    //cookie保存
    CookieStore cookieStore=new BasicCookieStore();

    // 这是执行方法  执行的ApiRequest
    public void execute(){
//        List<ApiRequest> requests = ApiRequest.getHttpRequest();
//        for(ApiRequest request : requests){
            HttpClientHandler handle = new HttpClientHandler(jobInfo,this);
            ApiRequestLog apiRequestLog = handle.execute();
            apiRequestLog.setJobId(jobInfo.getJobId());
            apiRequestLog.setJobInfoId(jobInfo.getId().toString());
//            httpRequestLogList.add(requestLog);
//        }
         apiRequestLog();

    }

    private void apiRequestLog(){
        apiRequestLog = new ApiRequestLog();
        StringBuffer tempLog = new StringBuffer();
        long costTime = 0l;
        for(HttpRequestLog log : httpRequestLogList){
            costTime += log.getCostTime();
            if(StringUtil.isNotEmpty(log.getLog())){
                if(tempLog.length() != 0){
                    tempLog.append("\r\n");
                }
                tempLog.append(log.getLog());
            }
        }
        httpSequenceLog.setPguid(httpSequence.getGuid());
        httpSequenceLog.setCostTime(costTime);
        httpSequenceLog.setLog(tempLog.toString());
        httpSequenceLog.setStatus(StringUtil.isEmpty(tempLog.toString()));
    }

}
