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
@TableName("http_sequence_log")
public class HttpSequenceLog implements Serializable {

	private static final long serialVersionUID=1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	
	private String pguid;
	
	private boolean status;//请求状态: false-失败,true-成功

	/**
	 * 请求耗时
	 */
	@TableField("costTime")
	private Integer costTime;

	/**
	 * 请求日志
	 */
	private String log;

	/**
	 * 创建时间
	 */
	@TableField("createTime")
	private LocalDateTime createTime;
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
                ", log='" + log  + '\'' +
                '}';
    }
    
    
}
