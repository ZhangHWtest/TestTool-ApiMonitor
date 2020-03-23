package com.apimonitor.httpclient.service;

import com.apimonitor.httpclient.entity.HttpSequenceLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * http序列日志表 服务类
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-19
 */
public interface HttpSequenceLogService extends IService<HttpSequenceLog> {
    public void cleanLog(int day);
}
