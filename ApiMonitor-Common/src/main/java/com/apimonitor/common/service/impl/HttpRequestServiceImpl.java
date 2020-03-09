package com.apimonitor.common.service.impl;

import com.apimonitor.common.entity.HttpRequest;
import com.apimonitor.common.entity.HttpRequestLog;
import com.apimonitor.common.entity.HttpSequence;
import com.apimonitor.common.http.client.HttpSequenceHandle;
import com.apimonitor.common.mapper.HttpRequestLogMapper;
import com.apimonitor.common.mapper.HttpRequestMapper;
import com.apimonitor.common.service.HttpRequestService;
import com.apimonitor.common.service.HttpSequenceService;
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
 * @since 2020-03-09
 */
@Service
public class HttpRequestServiceImpl extends ServiceImpl<HttpRequestMapper, HttpRequest> implements HttpRequestService {

    @Autowired
    private HttpRequestMapper httpRequestMapper;

    @Autowired
    private HttpSequenceService httpSequenceService;

    @Autowired
    private HttpRequestLogMapper httpRequestLogMapper;


    @Transactional
    @Override
    public void executeRequest(String guid) {
        HttpSequence instance = httpSequenceService.getByGuid(guid);
        if(instance==null){
            //删除任务
            return;
        }
//        List<HttpRequest> list = this.getHttpRequestListByPguid(guid);
//        instance.setHttpRequest(list);

        //执行接口探测
        HttpSequenceHandle httpSequenceHandle = new HttpSequenceHandle(instance);
//        httpSequenceHandle.execute();
        httpSequenceService.insertLog(httpSequenceHandle.httpSequenceLog);

        //保存日志
        for(HttpRequestLog httpRequestLog : httpSequenceHandle.httpRequestLogList){
            httpRequestLog.setPid(httpSequenceHandle.httpSequenceLog.getId());
            httpRequestLogMapper.insert(httpRequestLog);
        }
    }

    @Override
    public List<HttpRequest> getHttpRequestListByPguid(String pguid){
        return httpRequestMapper.getListByPguid(pguid);
    }
}
