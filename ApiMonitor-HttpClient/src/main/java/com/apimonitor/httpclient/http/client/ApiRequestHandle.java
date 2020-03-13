package com.apimonitor.httpclient.http.client;

import com.apimonitor.httpclient.entity.ApiRequest;
import com.apimonitor.httpclient.entity.ApiRequestLog;
import com.apimonitor.httpclient.entity.JobInfo;
import com.github.pagehelper.StringUtil;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ApiRequestHandle {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ApiRequestHandle.class);

    private ApiRequest apiRequest;
    public ApiRequestLog apiRequestLog;


    public HashMap<String,String> variables = new java.util.HashMap<String,String>();

    public ArrayList<ApiRequestLog> apiRequestLogList = new ArrayList<ApiRequestLog>();

    public ApiRequestHandle(ApiRequest apiRequest) {
        this.apiRequest = apiRequest;
    }

    //cookie保存
    CookieStore cookieStore=new BasicCookieStore();

    // 这是执行方法  执行的ApiRequest
    public void execute(){
            HttpClientHandler handle = new HttpClientHandler(apiRequest,this);
            ApiRequestLog apiRequestLog = handle.execute();
            apiRequestLog.setJobId(apiRequest.getJobId());
            apiRequestLog.setJobInfoId(apiRequest.getId().toString());
            apiRequestLogList.add(apiRequestLog);
//        }
        apiRequestLog();

    }

    private void apiRequestLog(){
        apiRequestLog = new ApiRequestLog();
        StringBuffer tempLog = new StringBuffer();
        long costTime = 0l;
        for(ApiRequestLog log : apiRequestLogList){
            costTime += log.getCostTime();
            if(StringUtil.isNotEmpty(log.getLog())){
                if(tempLog.length() != 0){
                    tempLog.append("\r\n");
                }
                tempLog.append(log.getLog());
            }
        }
        apiRequestLog.setJobId(apiRequest.getJobId());
        apiRequestLog.setCostTime((int) costTime);
        apiRequestLog.setLog(tempLog.toString());
        apiRequestLog.setStatus(1);
    }



    public void setVariables(String key, String value){
        variables.put(key, value);
    }

    public String getVariables(String val){
        for (String variableName : variables.keySet()) {
            if(variableName.equals(val)){
                return variables.get(variableName);
            }
        }
        return null;
    }


}
