package com.apimonitor.httpclient.service;

import com.apimonitor.httpclient.entity.ApiRequest;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * http请求表 服务类
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-12
 */
public interface ApiRequestService extends IService<ApiRequest> {

    void executeRequest(String guid);



}
