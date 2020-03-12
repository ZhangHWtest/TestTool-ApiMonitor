package com.apimonitor.common.service.impl;

import com.apimonitor.common.entity.HttpSequenceLog;
import com.apimonitor.common.mapper.HttpSequenceLogMapper;
import com.apimonitor.common.service.HttpSequenceLogService;
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
public class HttpSequenceLogServiceImpl extends ServiceImpl<HttpSequenceLogMapper, HttpSequenceLog> implements HttpSequenceLogService {

}
