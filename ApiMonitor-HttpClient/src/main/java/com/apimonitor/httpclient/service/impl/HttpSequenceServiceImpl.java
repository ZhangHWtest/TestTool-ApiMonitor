package com.apimonitor.httpclient.service.impl;

import com.apimonitor.httpclient.entity.HttpSequence;
import com.apimonitor.httpclient.mapper.HttpSequenceMapper;
import com.apimonitor.httpclient.quartz.DynamicJobManager;
import com.apimonitor.httpclient.service.HttpSequenceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * http序列表 服务实现类
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-19
 */
@Service
public class HttpSequenceServiceImpl extends ServiceImpl<HttpSequenceMapper, HttpSequence> implements HttpSequenceService {

    @Autowired
    private HttpSequenceService httpSequenceService;

    @Autowired
    private HttpSequenceMapper httpSequenceMapper;

    @Override
    public void updateEnabled(String jobId, int enable) {
        httpSequenceMapper.updateEnabled(jobId,enable);
    }


    @Transactional
    @Override
    public boolean disableMonitor(String jobId) {
        HttpSequence httpSequence = httpSequenceService.getOne(new QueryWrapper<HttpSequence>().eq("job_id",jobId));
        DynamicJobManager dynamicJobManager = new DynamicJobManager(httpSequence);
        return dynamicJobManager.kill();
    }


    @Transactional
    @Override
    public boolean enableMonitor(String jobId) {
        HttpSequence httpSequence = httpSequenceService.getOne(new QueryWrapper<HttpSequence>().eq("job_id",jobId));
        DynamicJobManager dynamicJobManager = new DynamicJobManager(httpSequence);
        return dynamicJobManager.enable();
    }
}
