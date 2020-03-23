package com.apimonitor.httpclient.service.impl;

import com.apimonitor.httpclient.entity.HttpSystem;
import com.apimonitor.httpclient.mapper.HttpSystemMapper;
import com.apimonitor.httpclient.service.HttpSystemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统名称表 服务实现类
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-19
 */
@Service
public class HttpSystemServiceImpl extends ServiceImpl<HttpSystemMapper, HttpSystem> implements HttpSystemService {

}
