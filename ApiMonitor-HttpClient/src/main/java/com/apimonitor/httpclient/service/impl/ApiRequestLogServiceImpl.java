package com.apimonitor.httpclient.service.impl;

import com.apimonitor.httpclient.entity.ApiRequestLog;
import com.apimonitor.httpclient.entity.JobInfoLog;
import com.apimonitor.httpclient.mapper.ApiRequestLogMapper;
import com.apimonitor.httpclient.service.ApiRequestLogService;
import com.apimonitor.httpclient.service.JobInfoLogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * http请求日志表 服务实现类
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-12
 */
@Service
public class ApiRequestLogServiceImpl extends ServiceImpl<ApiRequestLogMapper, ApiRequestLog> implements ApiRequestLogService {

    @Autowired
    private ApiRequestLogService apiRequestLogService;

    @Autowired
    private JobInfoLogService jobInfoLogService;
    @Override
    public void cleanMonitorLogs(int day) {
        apiRequestLogService.remove(new QueryWrapper<ApiRequestLog>().ge("create_time",day));
        jobInfoLogService.remove(new QueryWrapper<JobInfoLog>().ge("create_time",day));
    }
}
