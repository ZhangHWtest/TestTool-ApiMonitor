package com.apimonitor.httpclient.quartz;


import com.apimonitor.httpclient.context.BeanProvider;
import com.apimonitor.httpclient.entity.HttpSequence;
import com.apimonitor.httpclient.service.HttpSequenceService;
import org.apache.commons.lang.StringUtils;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class DynamicJobManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicJobManager.class);

    private static final String MONITORING_INSTANCE_JOB_NAME_PREFIX = "monitoring-instance-";


    public static String generateMonitoringInstanceJobName(String key) {
        return MONITORING_INSTANCE_JOB_NAME_PREFIX + key;
    }



    private transient HttpSequenceService httpSequenceService = BeanProvider.getBean(HttpSequenceService.class);

    private HttpSequence httpSequence;
    
    public DynamicJobManager(HttpSequence httpSequence) {
        this.httpSequence = httpSequence;
    }



    /**
     * 判断监控是否启用中
     * @return
     */
    public boolean enable() {
        //final ApplicationInstance instance = instanceRepository.findByGuid(guid, ApplicationInstance.class);
        //已经启用，忽略它
        if (httpSequence.getEnabled() ==1) {
            LOGGER.debug("<{}> Instance[guid={}] already enabled, ignore it", username(), httpSequence.getJobId());
            return false;
        }

        final boolean addSuccessful = startupMonitoringJob(httpSequence);
        if (!addSuccessful) {
            LOGGER.debug("<{}> NOTE: Add MonitoringJob[jobName={}] failed", username(), httpSequence.getJobName());
            return false;
        }

        //update
        httpSequence.setEnabled(1);
        httpSequenceService.updateEnabled(httpSequence.getJobId(),1);
        LOGGER.debug("<{}> Update ApplicationInstance[guid={}] enabled=true,jobName={}", username(), httpSequence.getJobId(), httpSequence.getJobName());

        return true;
    }

    private boolean startupMonitoringJob(HttpSequence httpSequence) {
        final String jobName = getAndSetJobName(httpSequence);

        DynamicJob job = new DynamicJob(jobName)
                .cronExpression(httpSequence.getJobCron())
                .target(HttpMonitoringJob.class)
                .addJobData(HttpMonitoringJob.APPLICATION_INSTANCE_GUID, httpSequence.getJobId());

        return executeStartup(httpSequence, job);
    }

    private boolean executeStartup(HttpSequence httpSequence, DynamicJob job) {
        boolean result = false;
        try {
            if (DynamicSchedulerFactory.existJob(job)) {
                result = DynamicSchedulerFactory.resumeJob(job);
                LOGGER.debug("<{}> Resume  [{}] by ApplicationInstance[guid={},instanceName={}] result: {}", username(), job, httpSequence.getJobId(), httpSequence.getJobName(), result);
            } else {
                result = DynamicSchedulerFactory.registerJob(job);
                LOGGER.debug("<{}> Register  [{}] by ApplicationInstance[guid={},instanceName={}] result: {}", username(), job, httpSequence.getJobId(), httpSequence.getJobName(), result);
            }
        } catch (SchedulerException e) {
            LOGGER.error("<{}> Register [" + job + "] failed", username(), e);
        }
        return result;
    }

    private String getAndSetJobName(HttpSequence httpSequence) {
        String jobName = httpSequence.getJobName();
        if (StringUtils.isEmpty(jobName)) {
            jobName = generateMonitoringInstanceJobName(httpSequence.getJobId());
            httpSequence.setJobName(jobName);
        }
        return jobName;
    }

    private String username() {
    	return null;
        //return SecurityUtils.currentUsername();
    }
    

    public boolean delete() {
        if (httpSequence.getEnabled() ==1) {
            LOGGER.debug("<{}> Forbid delete enabled ApplicationInstance[guid={}]", username(), httpSequence.getJobId());
            return false;
        }

        //httpRequestService.deleteHttpLog(instance.getGuid());

        checkAndRemoveJob(httpSequence);

        //logic delete  删除Archived置为1
//        httpSequence.setArchived(true);
//        httpRequestService.archivedHttpData(instance.getGuid());
//        LOGGER.debug("<{}> Archive ApplicationInstance[guid={}] and FrequencyMonitorLogs,MonitoringReminderLogs", username(), instance.getGuid());
        return true;
    }

    private void checkAndRemoveJob(HttpSequence httpSequence) {
        DynamicJob job = new DynamicJob(getAndSetJobName(httpSequence));
        try {
            if (DynamicSchedulerFactory.existJob(job)) {
                final boolean result = DynamicSchedulerFactory.removeJob(job);
                LOGGER.debug("<{}> Remove DynamicJob[{}] result [{}]", username(), job, result);
            }
        } catch (SchedulerException e) {
            LOGGER.error("<{}> Remove [" + job + "] failed", username(), e);
        }
    }

    
    /* * 1. Remove the job
     * 2. update instance to enabled=false
     **/
     
    public boolean kill() {
        if (httpSequence.getEnabled()==0) {
            LOGGER.debug("<{}> Expect ApplicationInstance[guid={}] enabled=true,but it is false, illegal status",
            		username(), httpSequence.getJobId());
            return false;
        }

        if (!pauseJob(httpSequence)) {
            LOGGER.debug("<{}> Pause Job[name={}] failed", username(), httpSequence.getJobName());
            return false;
        }

        //update
        httpSequence.setEnabled(0);
        httpSequenceService.updateEnabled(httpSequence.getJobId(),0);
        LOGGER.debug("<{}> Update ApplicationInstance[guid={}] enabled=false", username(), httpSequence.getJobId());
        return true;
    }

    private boolean pauseJob(HttpSequence instance) {
        DynamicJob job = new DynamicJob(getAndSetJobName(instance));
        try {
            return DynamicSchedulerFactory.pauseJob(job);
        } catch (SchedulerException e) {
            LOGGER.error("<{}> Pause [" + job + "] failed", username(), e);
            return false;
        }
    }
}