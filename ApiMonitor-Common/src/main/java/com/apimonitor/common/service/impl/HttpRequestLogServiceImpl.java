package com.apimonitor.common.service.impl;

import com.apimonitor.common.entity.HttpRequestLog;
import com.apimonitor.common.mapper.HttpRequestLogMapper;
import com.apimonitor.common.service.HttpRequestLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * http请求日志表 服务实现类
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-09
 */
@Service
public class HttpRequestLogServiceImpl extends ServiceImpl<HttpRequestLogMapper, HttpRequestLog> implements HttpRequestLogService {

}
