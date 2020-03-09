package com.apimonitor.common.quartz;


import com.apimonitor.common.context.BeanProvider;
import com.apimonitor.common.entity.HttpSequence;
import com.apimonitor.common.job.HttpMonitoringJob;
import com.apimonitor.common.service.HttpRequestService;
import org.apache.commons.lang.StringUtils;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhanghw
 */
public class DynamicJobManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicJobManager.class);

    private static final String MONITORING_INSTANCE_JOB_NAME_PREFIX = "monitoring-instance-";


    public static String generateMonitoringInstanceJobName(String key) {
        return MONITORING_INSTANCE_JOB_NAME_PREFIX + key;
    }

    private transient HttpRequestService httpRequestService = BeanProvider.getBean(HttpRequestService.class);

    
    private HttpSequence httpSequence;
    
    public DynamicJobManager(HttpSequence httpSequence) {
        this.httpSequence = httpSequence;
    }


    public boolean enable() {
        //final ApplicationInstance instance = instanceRepository.findByGuid(guid, ApplicationInstance.class);
        // 判断是否正在运行
        if (httpSequence.getEnabled() ==1) {
            LOGGER.debug("<{}> Instance[guid={}] already enabled, ignore it", username(), httpSequence.getgId());
            return false;
        }

        final boolean addSuccessful = startupMonitoringJob(httpSequence);
        if (!addSuccessful) {
            LOGGER.debug("<{}> NOTE: Add MonitoringJob[jobName={}] failed", username(), httpSequence.getJobName());
            return false;
        }

        //update
//        httpSequence.setEnabled(1);
//        httpRequestService.updateEnabled(instance);
//        LOGGER.debug("<{}> Update ApplicationInstance[guid={}] enabled=true,jobName={}", username(), instance.getGuid(), instance.getJobName());

        return true;
    }

    private boolean startupMonitoringJob(HttpSequence httpSequence) {
        final String jobName = getAndSetJobName(httpSequence);

        DynamicJob job = new DynamicJob(jobName)
                // 监控频率表达式，先写死
                .cronExpression("0/10 * * * * ?")
                .target(HttpMonitoringJob.class)
                .addJobData(HttpMonitoringJob.APPLICATION_INSTANCE_GUID, httpSequence.getgId());

        return executeStartup(httpSequence, job);
    }



    private boolean executeStartup(HttpSequence httpSequence, DynamicJob job) {
        boolean result = false;
        try {
            if (DynamicSchedulerFactory.existJob(job)) {
                result = DynamicSchedulerFactory.resumeJob(job);
                LOGGER.debug("<{}> Resume  [{}] by ApplicationInstance[guid={},instanceName={}] result: {}", username(), job, httpSequence.getgId(), httpSequence.getJobName(), result);
            } else {
                result = DynamicSchedulerFactory.registerJob(job);
                LOGGER.debug("<{}> Register  [{}] by ApplicationInstance[guid={},instanceName={}] result: {}", username(), job, httpSequence.getgId(), httpSequence.getJobName(), result);
            }
        } catch (SchedulerException e) {
            LOGGER.error("<{}> Register [" + job + "] failed", username(), e);
        }
        return result;
    }

    private String getAndSetJobName(HttpSequence httpSequence) {
        String jobName = httpSequence.getJobName();
        if (StringUtils.isEmpty(jobName)) {
            jobName = generateMonitoringInstanceJobName(httpSequence.getgId().toString());
            httpSequence.setJobName(jobName);
        }
        return jobName;
    }

    private String username() {
    	return null;
    }

}