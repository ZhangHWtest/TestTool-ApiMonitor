package com.apimonitor.common.service.impl;

import com.apimonitor.admin.entity.HttpRequestLog;
import com.apimonitor.admin.mapper.HttpRequestLogMapper;
import com.apimonitor.system.service.HttpRequestLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * http请求日志表 服务实现类
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-03
 */
@Service
public class HttpRequestLogServiceImpl extends ServiceImpl<HttpRequestLogMapper, HttpRequestLog> implements HttpRequestLogService {

}
