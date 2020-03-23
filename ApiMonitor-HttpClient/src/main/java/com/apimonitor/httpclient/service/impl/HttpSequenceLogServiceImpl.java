package com.apimonitor.httpclient.service.impl;

import com.apimonitor.httpclient.entity.HttpSequenceLog;
import com.apimonitor.httpclient.mapper.HttpSequenceLogMapper;
import com.apimonitor.httpclient.service.HttpSequenceLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * http序列日志表 服务实现类
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-19
 */
@Service
public class HttpSequenceLogServiceImpl extends ServiceImpl<HttpSequenceLogMapper, HttpSequenceLog> implements HttpSequenceLogService {
    @Autowired
    private HttpSequenceLogMapper httpSequenceLogMapper;

    @Override
    public void cleanLog(int day){
        httpSequenceLogMapper.cleanLogByDay(day);

    }
}
