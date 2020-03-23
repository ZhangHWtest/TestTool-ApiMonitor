package com.apimonitor.httpclient.service;

import com.apimonitor.httpclient.entity.HttpSequence;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * http序列表 服务类
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-19
 */
public interface HttpSequenceService extends IService<HttpSequence> {
    void updateEnabled(String jobId, int enable);

    boolean disableMonitor(String jobId);

    boolean enableMonitor(String jobId);

}
