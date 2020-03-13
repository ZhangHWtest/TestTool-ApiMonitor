package com.apimonitor.httpclient.service;

import com.apimonitor.httpclient.entity.JobInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * http序列表 服务类
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-12
 */
public interface JobInfoService extends IService<JobInfo> {

    boolean disableMonitor(String jobId);

    boolean enableMonitor(String jobId);


}
