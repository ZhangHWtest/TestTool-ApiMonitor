package com.apimonitor.common.service.impl;


import com.apimonitor.common.entity.HttpRequest;
import com.apimonitor.common.mapper.HttpRequestMapper;
import com.apimonitor.common.service.HttpRequestService;
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
