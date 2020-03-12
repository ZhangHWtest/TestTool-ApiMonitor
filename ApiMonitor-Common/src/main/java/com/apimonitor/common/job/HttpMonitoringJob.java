package com.apimonitor.common.job;


import com.apimonitor.common.context.BeanProvider;
import com.apimonitor.common.service.HttpRequestService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Job 入口文件
 */
@DisallowConcurrentExecution
public class HttpMonitoringJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpMonitoringJob.class);
    public static final String APPLICATION_INSTANCE_GUID = "instanceGuid";

    private transient HttpRequestService instanceService = BeanProvider.getBean(HttpRequestService.class);

    public HttpMonitoringJob() {
    	
    }


    /**
    * 每次的监控会将 以下代码执行一次
    */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        final JobKey key = context.getJobDetail().getKey();
        LOGGER.debug("*****  Start execute Job [{}]", key);

        final String guid = context.getMergedJobDataMap().getString(APPLICATION_INSTANCE_GUID);
        instanceService.executeRequest(guid);

        LOGGER.debug("&&&&&  End execute Job [{}]", key);
    }
}