package com.apimonitor.httpclient.service;

import com.apimonitor.httpclient.entity.HttpRequestLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * http请求日志表 服务类
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-19
 */
public interface HttpRequestLogService extends IService<HttpRequestLog> {
    public void cleanMonitorLogs(int day);

}
