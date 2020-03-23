package com.apimonitor.httpclient.service;

import com.apimonitor.httpclient.entity.HttpRequest;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * http请求表 服务类
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-19
 */
public interface HttpRequestService extends IService<HttpRequest> {
    void executeRequest(String jobId);

}
