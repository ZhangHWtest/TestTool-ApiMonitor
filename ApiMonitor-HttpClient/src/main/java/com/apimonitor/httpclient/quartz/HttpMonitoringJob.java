package com.apimonitor.httpclient.quartz;

import com.apimonitor.httpclient.context.BeanProvider;
import com.apimonitor.httpclient.service.HttpRequestService;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;


/**
 * Job 入口文件
 */
@DisallowConcurrentExecution
public class HttpMonitoringJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpMonitoringJob.class);

    public static final String APPLICATION_INSTANCE_GUID = "instanceJobId";

    private transient HttpRequestService httpRequestService = BeanProvider.getBean(HttpRequestService.class);

    public HttpMonitoringJob() {
    }


    /**
    * 每次的监控会将 以下代码执行一次
     * JobExecutionContext是什么？
     * 当Scheduler调用一个Job，就会将JobExecutionContext传递给Job的execute()方法；
     * Job能通过JobExecutionContext对象访问到Quartz运行时候的环境以及Job本身的明细数据
     *
     * JobDataMap是什么？
     * 在进行任务调度时，JobDataMap存储在JobExecutionContext中，非常方便获取
     * JobDataMap可以用来装载任何可序列化的数据对象，当job实例对象被执行时这些参数对象会传递给它。
     * JobDataMap实现了JDK的Map接口，并且添加了一些非常方便的方法用来存取基本数据类型。
    */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        final JobKey key = context.getJobDetail().getKey();
        LOGGER.debug("*****  Start execute Job [{}]", key);


        final String jobId = context.getMergedJobDataMap().getString(APPLICATION_INSTANCE_GUID);
        httpRequestService.executeRequest(jobId);

        LOGGER.debug("&&&&&  End execute Job [{}]", key);
        try{
            Scheduler scheduler=context.getScheduler();
            Set<JobKey> jobKeySet = scheduler.getJobKeys(GroupMatcher.anyGroup());
            System.out.println("------------ job"+jobKeySet+"job ------------");
        }catch (SchedulerException e1){
            e1.getUnderlyingException();
        }




    }
}