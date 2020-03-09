package com.apimonitor.common.entity;


import java.time.LocalDateTime;

public class HttpRequestForm {

	public HttpSequence getHttpSequence(HttpRequestForm from) {
		HttpSequence httpSequence =new HttpSequence();
		httpSequence.setgId(from.getgId());
		httpSequence.setgName(from.getgName());
		httpSequence.setsName(from.getsName());
		httpSequence.setJobName(from.getJobName());
		httpSequence.setType(from.getType());
		httpSequence.setEnabled(from.getEnabled());
		httpSequence.setFrequency(from.getFrequency());
		httpSequence.setRemark(from.getRemark());
		httpSequence.setCreateTime(from.getCreateTime());
		httpSequence.setArchived(from.getArchived());
		return httpSequence;
	}

	public HttpRequest getHttpRequest(HttpRequestForm from) {
		HttpRequest httpRequest =new HttpRequest();
		httpRequest.setSepId(from.getSepId());
		httpRequest.setgId(from.getgId());
		httpRequest.setSort(from.getSort());
		httpRequest.setHttpUrl(from.httpUrl);
		httpRequest.setHttpMethod(from.getHttpMethod());
		httpRequest.setHttpHeaders(from.getHttpHeaders());
		httpRequest.setHttpParameters(from.getHttpParameters());
		httpRequest.setMaxConnectionSeconds(from.getMaxConnectionSeconds());
		httpRequest.setConditionType(from.getConditionType());
		httpRequest.setConditionBody(from.getConditionBody());
		httpRequest.setResultType(from.getResultType());
		httpRequest.setVariables(from.getVariables());
		httpRequest.setRemark(from.getRemark());
		httpRequest.setArchived(from.getArchived());
		httpRequest.setCreateTime(from.getCreateTime());
		return httpRequest;
	}

	public HttpSystem getHttpSystem(HttpRequestForm from) {
		HttpSystem httpSystem =new HttpSystem();
		httpSystem.setName(from.getgName());
		return httpSystem;
	}


	/**
	 * 父主键（即http_sequence表的guid）
	 */
	private Integer sepId;
	/**
	 * 序号
	 */
	private Integer sort;

	/**
	 * 地址
	 */
	private String httpUrl;

	/**
	 * HTTP类型（GET, HEAD, POST, PUT, DELETE）
	 */
	private String httpMethod;

	/**
	 * 请求头部，格式（key::value\nkey::value）
	 */
	private String httpHeaders;

	/**
	 * 请求参数，格式（key::value\nkey::value）
	 */
	private String httpParameters;

	/**
	 * 最大连接时间
	 */
	private Integer maxConnectionSeconds;

	/**
	 * 结果校验类型（CONTAINS, DOESNT_CONTAIN, STATUSCODE, DEFAULT）
	 */
	private String conditionType;

	/**
	 * 结果校验内容
	 */
	private String conditionBody;

	/**
	 * 返回结果的格式（XML, JSON）
	 */
	private String resultType;

	/**
	 * 变量定义，格式（key::value\nkey::value）
	 */
	private String variables;

	/**
	 * 系统ID
	 */
	private Integer gId;

	/**
	 * 所属系统
	 */
	private String gName;

	/**
	 * 名称
	 */
	private String sName;

	/**
	 * job名称
	 */
	private String jobName;

	/**
	 * 类型（1:单个SINGLE,2:群组 SEQUENCE）
	 */
	private Integer type;

	/**
	 * 是否启动监控（0-不启动，1-启动）
	 */
	private Integer enabled;

	/**
	 * 监控频率,默认30
	 */
	private Integer frequency;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;

	/**
	 * 存档（0-有效，1-删除）
	 */
	private Boolean archived;


	public Integer getSepId() {
		return sepId;
	}

	public void setSepId(Integer sepId) {
		this.sepId = sepId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getHttpUrl() {
		return httpUrl;
	}

	public void setHttpUrl(String httpUrl) {
		this.httpUrl = httpUrl;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public String getHttpHeaders() {
		return httpHeaders;
	}

	public void setHttpHeaders(String httpHeaders) {
		this.httpHeaders = httpHeaders;
	}

	public String getHttpParameters() {
		return httpParameters;
	}

	public void setHttpParameters(String httpParameters) {
		this.httpParameters = httpParameters;
	}

	public Integer getMaxConnectionSeconds() {
		return maxConnectionSeconds;
	}

	public void setMaxConnectionSeconds(Integer maxConnectionSeconds) {
		this.maxConnectionSeconds = maxConnectionSeconds;
	}

	public String getConditionType() {
		return conditionType;
	}

	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}

	public String getConditionBody() {
		return conditionBody;
	}

	public void setConditionBody(String conditionBody) {
		this.conditionBody = conditionBody;
	}

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	public String getVariables() {
		return variables;
	}

	public void setVariables(String variables) {
		this.variables = variables;
	}

	public Integer getgId() {
		return gId;
	}

	public void setgId(Integer gId) {
		this.gId = gId;
	}

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
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

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public Boolean getArchived() {
		return archived;
	}

	public void setArchived(Boolean archived) {
		this.archived = archived;
	}
}