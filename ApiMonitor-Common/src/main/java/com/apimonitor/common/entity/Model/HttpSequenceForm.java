package com.apimonitor.common.entity.Model;

import com.apimonitor.common.entity.HttpRequest;
import com.apimonitor.common.entity.HttpSequence;
import com.apimonitor.common.entity.HttpSystem;

import java.time.LocalDateTime;

public class HttpSequenceForm {

    public HttpSequence getHttpSequence(HttpSequenceForm  httpRequestForm){
        HttpSequence httpSequence=new HttpSequence();
        httpSequence.setId(httpRequestForm.getSequenceId());
        httpSequence.setSystemId(httpRequestForm.getSystemId());
        httpSequence.setRequestId(httpRequestForm.getRequestId());
        httpSequence.setFrequency(httpRequestForm.getFrequency());
        httpSequence.setType(httpRequestForm.getType());
        httpSequence.setStatus(httpRequestForm.getStatus());
        httpSequence.setRemark(httpRequestForm.getRemark());
        httpSequence.setCreateTime(httpRequestForm.getCreateTime());

        return httpSequence;
    }
    public HttpRequest getHttpRequest(HttpSequenceForm  httpRequestForm){
        HttpRequest httpRequest=new HttpRequest();


        return httpRequest;
    }
    public HttpSystem getHttpSystem(HttpSequenceForm  httpRequestForm){
        HttpSystem httpSystem =new HttpSystem();
        httpSystem.setSystemName(httpRequestForm.getSystemName());
        return httpSystem;
    }


    public Integer getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(Integer sequenceId) {
        this.sequenceId = sequenceId;
    }

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }


    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }


    public String getConditionType() {
        return conditionType;
    }

    public void setConditionType(String conditionType) {
        this.conditionType = conditionType;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }


    public String getVariables() {
        return variables;
    }

    public void setVariables(String variables) {
        this.variables = variables;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * 监控id
     */
    private Integer sequenceId;
    /**
     * 系统id
     */
    private Integer systemId;

    /**
     * 接口id
     */
    private Integer requestId;


    /**
     * 监控频率，默认30,单位秒
     */
    private Integer frequency;

    /**
     * 类型（0:单个SINGLE, 1:群组SEQUENCE）
     */
    private Integer type;

    /**
     * 是否启动监控（0:未启动，1:运行中）
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;


    /**
     * 地址
     */
    private String url;

    /**
     * HTTP类型（GET, HEAD, POST, PUT, DELETE）
     */
    private String httpMethod;

    /**
     * 请求头部，格式（key::value\nkey::value）
     */
    private String headers;

    /**
     * 请求参数，格式（key::value\nkey::value）
     */
    private String parameters;


    /**
     * 结果校验类型（CONTAINS, DOESNT_CONTAIN, STATUSCODE, DEFAULT）
     */
    private String conditionType;

    /**
     * 结果校验内容
     */
    private String condition;

    /**
     * 变量定义，格式（key::value\nkey::value）
     */
    private String variables;

    /**
     * 系统名称
     */
    private String systemName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;





}
