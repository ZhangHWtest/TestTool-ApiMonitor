package com.apimonitor.common.service;

import com.apimonitor.common.entity.HttpSequence;
import com.apimonitor.common.entity.HttpSequenceLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * http序列表 服务类
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-09
 */
public interface HttpSequenceService extends IService<HttpSequence> {

    HttpSequence getByGuid(String guid);

    void insertLog(HttpSequenceLog httpSequenceLog);

}
