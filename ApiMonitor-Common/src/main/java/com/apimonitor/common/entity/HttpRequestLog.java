package com.apimonitor.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("http_request_log")
public class HttpRequestLog implements Serializable {


	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	
	private int pid;
	
	private String ppguid;
	
	private String pguid;

	private boolean status;//请求状态: false-失败,true-成功

	/**
	 * 请求耗时
	 */
	@TableField("costTime")
	private Integer costTime;

	/**
	 * 响应状态码
	 */
	@TableField("statusCode")
	private String statusCode;

	/**
	 * 响应结果
	 */
	@TableField("responseBody")
	private String responseBody;
    
    private String log;
	@TableField("createTime")
    private LocalDateTime createTime;
    
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPpguid() {
		return ppguid;
	}

	public void setPpguid(String ppguid) {
		this.ppguid = ppguid;
	}

	public String getPguid() {
		return pguid;
	}

	public void setPguid(String pguid) {
		this.pguid = pguid;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public long getCostTime() {
		return costTime;
	}

	public void setCostTime(Integer costTime) {
		this.costTime = costTime;
	}


	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

    public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}
    @Override
    public String toString() {
        return "{" +
                "status=" + status +
                ", costTime=" + costTime +
                ", statusCode='" + statusCode + '\'' +
                ", responseBody='" + responseBody + '\'' +
                ", log='" + log  + '\'' +
                '}';
    }
    
    
}
