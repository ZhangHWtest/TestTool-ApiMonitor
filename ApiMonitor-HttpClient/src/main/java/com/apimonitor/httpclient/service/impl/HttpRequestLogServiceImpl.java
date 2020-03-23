package com.apimonitor.httpclient.service.impl;

import com.apimonitor.httpclient.entity.HttpRequestLog;
import com.apimonitor.httpclient.mapper.HttpRequestLogMapper;
import com.apimonitor.httpclient.service.HttpRequestLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * http请求日志表 服务实现类
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-19
 */
@Service
public class HttpRequestLogServiceImpl extends ServiceImpl<HttpRequestLogMapper, HttpRequestLog> implements HttpRequestLogService {
   @Autowired
   private HttpRequestLogMapper httpRequestLogMapper;

    @Transactional
    @Override
    public void cleanMonitorLogs(int day) {
        httpRequestLogMapper.cleanLogByDay(day);

    }
}
