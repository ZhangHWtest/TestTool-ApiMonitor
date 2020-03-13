package com.apimonitor.httpclient.quartz;

import com.apimonitor.httpclient.entity.JobInfo;
import com.apimonitor.httpclient.entity.model.MonitorFrequency;
import com.apimonitor.httpclient.job.HttpMonitoringJob;
import org.apache.commons.lang.StringUtils;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamicJobManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicJobManager.class);

    private static final String MONITORING_INSTANCE_JOB_NAME_PREFIX = "jobName-";


    public static String generateMonitoringInstanceJobName(String key) {
        return MONITORING_INSTANCE_JOB_NAME_PREFIX + key;
    }

    private JobInfo jobInfo;

    public DynamicJobManager(JobInfo jobInfo){
        this.jobInfo=jobInfo;
    }

    /**
     * 判断监控是否启用中
     */

    public boolean enable(){

        if(jobInfo.getStatus()==1 ){
            LOGGER.debug("<{}> Instance[guid={}] already enabled, ignore it", username(), jobInfo.getJobId());
            return false;
        }
        final boolean addSuccessful = startupMonitoringJob(jobInfo);
        if (!addSuccessful) {
            LOGGER.debug("<{}> NOTE: Add MonitoringJob[jobName={}] failed", username(), jobInfo.getJobName());
            return false;
        }



        return  true;
    }


    private boolean startupMonitoringJob(JobInfo jobInfo) {
        final String jobName = getAndSetJobName(jobInfo);

        DynamicJob job = new DynamicJob(jobName)
                //.cronExpression("0/5 * * * * ?")
                .cronExpression(getCronExpression(jobInfo.getFrequency()).toString())
                .target(HttpMonitoringJob.class)
                .addJobData(HttpMonitoringJob.APPLICATION_INSTANCE_JOBID, jobInfo.getJobId());

        return executeStartup(jobInfo, job);
    }

    private boolean executeStartup(JobInfo jobInfo, DynamicJob job) {
        boolean result = false;
        try {
            if (DynamicSchedulerFactory.existJob(job)) {
                result = DynamicSchedulerFactory.resumeJob(job);
                LOGGER.debug("<{}> Resume  [{}] by ApplicationInstance[guid={},instanceName={}] result: {}", username(), job, jobInfo.getJobId(), jobInfo.getJobName(), result);
            } else {
                result = DynamicSchedulerFactory.registerJob(job);
                LOGGER.debug("<{}> Register  [{}] by ApplicationInstance[guid={},instanceName={}] result: {}", username(), job, jobInfo.getJobId(), jobInfo.getJobName(), result);
            }
        } catch (SchedulerException e) {
            LOGGER.error("<{}> Register [" + job + "] failed", username(), e);
        }
        return result;
    }


    //判断 jobName 是否为空，空就讲jobId 赋给名字
    private String getAndSetJobName(JobInfo jobInfo) {
        String jobName = jobInfo.getJobName();
        if (StringUtils.isEmpty(jobName)) {
            jobName = generateMonitoringInstanceJobName(jobInfo.getJobId().toString());
            jobInfo.setJobName(jobName);
        }
        return jobName;
    }


    private String username() {
        return null;
        //return SecurityUtils.currentUsername();
    }

    private MonitorFrequency getCronExpression (Integer second){
        if(second ==5){
            return MonitorFrequency.FIVE;
        }else if(second ==10){
            return MonitorFrequency.TEN;
        }else if(second ==20){
            return MonitorFrequency.TWENTY;
        }else if(second ==30){
            return MonitorFrequency.THIRTY;
        }else if(second ==60){
            return MonitorFrequency.SIXTY;
        }else if(second ==120){
            return MonitorFrequency.TWO_MINUTES;
        }else if(second ==180){
            return MonitorFrequency.THREE_MINUTES;
        }else if(second ==300) {
            return MonitorFrequency.FIVE_MINUTES;
        }else if(second ==1800) {
            return MonitorFrequency.THIRTY_MINUTES;
        }else if(second ==3600) {
            return MonitorFrequency.ONE_HOUR;
        }
        return MonitorFrequency.THIRTY;
    }


    /* * 1. Remove the job
     * 2. update instance to enabled=false
     **/

    public boolean kill() {
//        if (!jobInfo.isEnabled()) {
//            LOGGER.debug("<{}> Expect ApplicationInstance[guid={}] enabled=true,but it is false, illegal status",
//                    username(), jobInfo.getGuid());
//            return false;
//        }
//
//        if (!pauseJob(instance)) {
//            LOGGER.debug("<{}> Pause Job[name={}] failed", username(), instance.getJobName());
//            return false;
//        }
//
//        //update
//        instance.setEnabled(false);
//        httpRequestService.updateEnabled(instance);
//        LOGGER.debug("<{}> Update ApplicationInstance[guid={}] enabled=false", username(), instance.getGuid());
        return true;
    }

}
