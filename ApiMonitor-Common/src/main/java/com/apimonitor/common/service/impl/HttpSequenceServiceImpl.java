package com.apimonitor.common.service.impl;

import com.apimonitor.common.entity.HttpSequence;
import com.apimonitor.common.entity.HttpSequenceLog;
import com.apimonitor.common.mapper.HttpSequenceLogMapper;
import com.apimonitor.common.mapper.HttpSequenceMapper;
import com.apimonitor.common.service.HttpSequenceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * http序列表 服务实现类
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-09
 */
@Service
public class HttpSequenceServiceImpl extends ServiceImpl<HttpSequenceMapper, HttpSequence> implements HttpSequenceService {

    @Autowired
    private HttpSequenceMapper httpSequenceMapper;
    @Autowired
    private HttpSequenceLogMapper httpSequenceLogMapper;

    @Override
    public HttpSequence getByGuid(String gId){
        return httpSequenceMapper.getByGuid(gId);
    }

    @Override
    public void insertLog(HttpSequenceLog httpSequenceLog){
        httpSequenceLogMapper.insert(httpSequenceLog);
    }

}
