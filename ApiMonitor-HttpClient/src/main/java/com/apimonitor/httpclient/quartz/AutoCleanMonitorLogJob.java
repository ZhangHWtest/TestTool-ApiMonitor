package com.apimonitor.httpclient.quartz;

import com.apimonitor.httpclient.service.HttpRequestLogService;
import com.apimonitor.httpclient.service.HttpRequestService;
import com.apimonitor.httpclient.service.HttpSequenceLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * log内容： 清理类
 * 实现：InitializingBean接口
 * 为bean提供了初始化方法的方式，它只包括afterPropertiesSet方法，凡是继承该接口的类，在初始化bean的时候都会执行该方法。
 */
@Component
public class AutoCleanMonitorLogJob implements InitializingBean {

    private static final Logger LOG = LoggerFactory.getLogger(AutoCleanMonitorLogJob.class);

    // 清楚log
    @Autowired
    private transient HttpSequenceLogService httpSequenceLogService;
    @Autowired
    private transient HttpRequestLogService httpRequestLogService;

    public AutoCleanMonitorLogJob() {
    }

    @Value("${auto.clean.monitor.log.keep.day}")
    private int day;

    @Scheduled(cron = "${auto.clean.monitor.log.cron.expression}")
    public void execute() {
        LOG.debug("*****  Start execute Job [{}]", getClass());
        // 调用service 的方法
        httpSequenceLogService.cleanLog(day);
        httpRequestLogService.cleanMonitorLogs(day);

        LOG.debug("&&&&&  End execute Job [{}]", getClass());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(httpSequenceLogService, "logService is null");
        Assert.notNull(httpRequestLogService, "logService is null");
    }

}
