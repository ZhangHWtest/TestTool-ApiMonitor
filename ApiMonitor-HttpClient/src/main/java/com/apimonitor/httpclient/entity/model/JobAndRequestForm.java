package com.apimonitor.httpclient.entity.model;

import com.apimonitor.httpclient.entity.ApiRequest;
import com.apimonitor.httpclient.entity.JobInfo;

import java.time.LocalDateTime;

public class JobAndRequestForm {

    public JobInfo getJobInfo(JobAndRequestForm from){
        JobInfo jobInfo =new JobInfo();
        jobInfo.setJobId(from.getJobId());
        jobInfo.setJobGroup(from.getJobGroup());
        jobInfo.setJobName(from.getJobName());
        jobInfo.setType(from.getType());
        jobInfo.setFrequency(from.getFrequency());
        jobInfo.setStatus(from.getStatus());
        jobInfo.setRemark(from.getRemark());
        jobInfo.setCreateBy(from.getCreateBy());
        jobInfo.setCreateTime(from.getCreateTime());
        jobInfo.setUpdateBy(from.getUpdateBy());
        jobInfo.setUpdateTime(from.getUpdateTime());
        return jobInfo;
    }
    public ApiRequest getApiRequest(JobAndRequestForm from){
        ApiRequest apiRequest =new ApiRequest();
        apiRequest.setJobId(from.getJobId());
        apiRequest.setSort(from.getSort());
        apiRequest.setApiUrl(from.getApiUrl());
        apiRequest.setApiMethod(from.getApiMethod());
        apiRequest.setApiHeaders(from.getApiHeaders());
        apiRequest.setApiParameters(from.getApiParameters());
        apiRequest.setConditionType(from.getConditionType());
        apiRequest.setConditionBody(from.getConditionBody());
        apiRequest.setRemark(from.getRemark());
        apiRequest.setCreateTime(from.getCreateTime());
        return apiRequest;
    }

    private String jobId;

    /**
     * 所属系统
     */
    private String jobGroup;


    /**
     * job名称
     */
    private String jobName;

    /**
     * 类型（1：SINGLE, 2SEQUENCE）
     */
    private Integer type;

    /**
     * 监控频率表达式例：0/5 * * * * ?
     */
    private String frequency;

    /**
     * 是否启动监控（0-不启动，1-启动）
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;



    private Integer apiRequestId;


    /**
     * 序号
     */
    private Integer sort;

    /**
     * 地址
     */
    private String apiUrl;

    /**
     * HTTP类型（1:GET,2: HEAD,3: POST,4: PUT,5: DELETE）
     */
    private Integer apiMethod;

    /**
     * 请求头部，格式（key::value\nkey::value）
     */
    private String apiHeaders;

    /**
     * 请求参数，格式（key::value\nkey::value）
     */
    private String apiParameters;

    /**
     * 结果校验类型（1:CONTAINS,2: DOESNT_CONTAIN,3: STATUSCODE,4: DEFAULT）
     */
    private Integer conditionType;

    /**
     * 结果校验内容
     */
    private String conditionBody;


    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }


    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getApiRequestId() {
        return apiRequestId;
    }

    public void setApiRequestId(Integer apiRequestId) {
        this.apiRequestId = apiRequestId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public Integer getApiMethod() {
        return apiMethod;
    }

    public void setApiMethod(Integer apiMethod) {
        this.apiMethod = apiMethod;
    }

    public String getApiHeaders() {
        return apiHeaders;
    }

    public void setApiHeaders(String apiHeaders) {
        this.apiHeaders = apiHeaders;
    }

    public String getApiParameters() {
        return apiParameters;
    }

    public void setApiParameters(String apiParameters) {
        this.apiParameters = apiParameters;
    }

    public Integer getConditionType() {
        return conditionType;
    }

    public void setConditionType(Integer conditionType) {
        this.conditionType = conditionType;
    }

    public String getConditionBody() {
        return conditionBody;
    }

    public void setConditionBody(String conditionBody) {
        this.conditionBody = conditionBody;
    }
}
