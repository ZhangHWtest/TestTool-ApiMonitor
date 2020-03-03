package com.apimonitor.common.service.impl;

import com.apimonitor.admin.entity.HttpRequest;
import com.apimonitor.admin.mapper.HttpRequestMapper;
import com.apimonitor.system.service.HttpRequestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * http请求表 服务实现类
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-03
 */
@Service
public class HttpRequestServiceImpl extends ServiceImpl<HttpRequestMapper, HttpRequest> implements HttpRequestService {

}
