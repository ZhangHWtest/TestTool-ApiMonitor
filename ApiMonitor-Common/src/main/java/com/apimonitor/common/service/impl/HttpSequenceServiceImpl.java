package com.apimonitor.common.service.impl;

import com.apimonitor.admin.entity.HttpSequence;
import com.apimonitor.admin.mapper.HttpSequenceMapper;
import com.apimonitor.system.service.HttpSequenceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * http序列表 服务实现类
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-03
 */
@Service
public class HttpSequenceServiceImpl extends ServiceImpl<HttpSequenceMapper, HttpSequence> implements HttpSequenceService {

}
