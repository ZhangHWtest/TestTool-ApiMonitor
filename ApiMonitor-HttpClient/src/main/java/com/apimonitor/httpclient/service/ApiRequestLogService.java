package com.apimonitor.httpclient.service;

import com.apimonitor.httpclient.entity.ApiRequestLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * http请求日志表 服务类
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-12
 */
public interface ApiRequestLogService extends IService<ApiRequestLog> {

    void cleanMonitorLogs(int day);

}
