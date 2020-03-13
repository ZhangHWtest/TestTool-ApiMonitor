package com.apimonitor.httpclient.service.impl;

import com.apimonitor.httpclient.entity.JobInfoLog;
import com.apimonitor.httpclient.mapper.JobInfoLogMapper;
import com.apimonitor.httpclient.service.JobInfoLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * http序列日志表 服务实现类
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-12
 */
@Service
public class JobInfoLogServiceImpl extends ServiceImpl<JobInfoLogMapper, JobInfoLog> implements JobInfoLogService {

}
