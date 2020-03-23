package com.apimonitor.httpclient.entity.model;


import com.apimonitor.httpclient.entity.HttpRequest;
import com.apimonitor.httpclient.entity.HttpSequence;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MonitorForm {

    public HttpSequence getHttpSequence(MonitorForm from){
        HttpSequence httpSequence =new HttpSequence();
        httpSequence.setJobId(from.getJobId());
        httpSequence.setJobName(from.getJobName());
        httpSequence.setHttpSystemId(from.getHttpSystemId());
        httpSequence.setHttpName(from.getHttpName());
        httpSequence.setType(from.getType());
        httpSequence.setRemark(from.getRemark());
        httpSequence.setJobCron(from.getJobCron());
        httpSequence.setCreateTime(from.getCreateTime());
        return httpSequence;
    }

    public HttpRequest getHttpRequest(MonitorForm from){
        HttpRequest httpRequest =new HttpRequest();
        httpRequest.setJobId(from.getJobId());
        httpRequest.setHttpUrl(from.getHttpUrl());
        httpRequest.setHttpMethod(from.getHttpMethod());
        httpRequest.setHttpHeaders(from.getHttpHeaders());
        httpRequest.setHttpBody(from.getHttpBody());
        httpRequest.setConditionType(from.getConditionType());
        httpRequest.setConditionBody(from.getConditionBody());
        httpRequest.setRemark(from.getRemark());
        httpRequest.setCreateTime(from.getCreateTime());

        return httpRequest;
    }

    private String jobId;

    /**
     * job名称
     */
    private String jobName;

    /**
     * 所属系统
     */
    private Integer httpSystemId;

    /**
     * 名称
     */
    private String httpName;

    /**
     * 类型（0:SINGLE,1: SEQUENCE）
     */
    private Integer type;

    /**
     * 备注
     */
    private String remark;


    /**
     * 监控频率，默认THIRTY30秒
     */
    private String jobCron;

    /**
     * 地址
     */
    private String httpUrl;

    /**
     * HTTP类型（GET, HEAD, POST, PUT, DELETE）
     */
    private Integer httpMethod;

    /**
     * 请求头部，格式（key::value\nkey::value）
     */
    private String httpHeaders;

    /**
     * 请求参数，格式（key::value\nkey::value）
     */
    private String httpBody;


    /**
     * 结果校验类型（CONTAINS, DOESNT_CONTAIN, STATUSCODE, DEFAULT）
     */
    private Integer conditionType;

    /**
     * 结果校验内容
     */
    private String conditionBody;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
