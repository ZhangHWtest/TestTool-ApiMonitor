package com.apimonitor.common.service.impl;

import com.apimonitor.common.entity.HttpSystem;
import com.apimonitor.common.mapper.HttpSystemMapper;
import com.apimonitor.common.service.HttpSystemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统名称表 服务实现类
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-06
 */
@Service
public class HttpSystemServiceImpl extends ServiceImpl<HttpSystemMapper, HttpSystem> implements HttpSystemService {

}
