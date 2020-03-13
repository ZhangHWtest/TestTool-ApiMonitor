package com.apimonitor.httpclient.service.impl;

import com.apimonitor.httpclient.entity.ApiRequest;
import com.apimonitor.httpclient.entity.JobInfo;
import com.apimonitor.httpclient.mapper.JobInfoMapper;
import com.apimonitor.httpclient.quartz.DynamicJobManager;
import com.apimonitor.httpclient.service.JobInfoService;
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
 * @since 2020-03-12
 */
@Service
public class JobInfoServiceImpl extends ServiceImpl<JobInfoMapper, JobInfo> implements JobInfoService {

    @Autowired
    private JobInfoService jobInfoService;

    @Transactional
    @Override
    public boolean enableMonitor(String jobId) {
        JobInfo jobInfo = jobInfoService.getOne(new QueryWrapper<JobInfo>().eq("job_id",jobId));
        DynamicJobManager dynamicJobManager = new DynamicJobManager(jobInfo);
        return dynamicJobManager.enable();
    }

    @Transactional
    @Override
    public boolean disableMonitor(String jobId) {
        JobInfo jobInfo = jobInfoService.getOne(new QueryWrapper<JobInfo>().eq("job_id",jobId));
        DynamicJobManager dynamicJobManager = new DynamicJobManager(jobInfo);
        return dynamicJobManager.kill();
    }

}
