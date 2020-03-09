package com.apimonitor.common.service;

import com.apimonitor.common.entity.HttpRequest;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * http请求表 服务类
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-09
 */
public interface HttpRequestService extends IService<HttpRequest> {
    void executeRequest(String gId);

    List<HttpRequest> getHttpRequestListByPguid(String pguid);

}
