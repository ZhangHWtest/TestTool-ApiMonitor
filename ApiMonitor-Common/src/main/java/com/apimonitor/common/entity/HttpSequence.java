package com.apimonitor.common.entity;

import com.apimonitor.common.entity.modelForm.HttpRequestForm;
import com.apimonitor.common.entity.modelForm.MonitorFrequency;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("http_sequence")
public class HttpSequence implements Serializable {

	public HttpSequence(){
		
	}
	public static HttpSequence getHttpSequence(HttpRequestForm httpRequestForm){
		HttpSequence h = new HttpSequence();
		h.setGuid(httpRequestForm.getPguid());
		h.setGroup(httpRequestForm.getGroup());
		h.setType(httpRequestForm.getType());
		h.setName( httpRequestForm.getName());
		h.setFrequency(httpRequestForm.getFrequency());
		h.setRemark(httpRequestForm.getRemark());
		return h;
	}
	
	public enum MonitorType {
		SINGLE, SEQUENCE
	}
	private List<HttpRequest> httpRequest;

	private HashMap<String,String> variableResultMap = new HashMap<String,String>();


	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	
	private String guid;
	
	private String groupName;//所属组
	
	private MonitorType type;//类型：SINGLE-单个接口；SEQUENCE-多个接口
	
	private String name;

    private MonitorFrequency frequency = MonitorFrequency.THIRTY;//监控频率
    
	private String variableResult;
	
    private String remark;

	@TableField("jobName")
    private String jobName;//quartz调度的job名称

    private boolean enabled;//是否启动监控

    private boolean archived;//是否删除（0-有效，1-删除）

	@TableField("createTime")
	private LocalDateTime createTime;
    
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<HttpRequest> getHttpRequest() {
		return httpRequest;
	}

	public void setHttpRequest(List<HttpRequest> httpRequest) {
		this.httpRequest = httpRequest;
	}
	
	public HashMap<String, String> getVariableResultMap() {
		return variableResultMap;
	}

	public void setVariableResultMap(HashMap<String, String> variableResultMap) {
		this.variableResultMap = variableResultMap;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getGroup() {
		return groupName;
	}

	public void setGroup(String group) {
		this.groupName = group;
	}

	public String getVariableResult() {
		return variableResult;
	}

	public void setVariableResult(String variableResult) {
		this.variableResult = variableResult;
	}

	public MonitorType getType() {
		return type;
	}

	public void setType(MonitorType type) {
		this.type = type;
	}
	

	public MonitorFrequency getFrequency() {
		return frequency;
	}

	public void setFrequency(MonitorFrequency frequency) {
		this.frequency = frequency;
	}


	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

    public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public boolean isArchived() {
		return archived;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}
	
	public static String getMonitorTypeName(String type){
		return "SEQUENCE".equals(type) ? "多API":"单API";
	}
}
