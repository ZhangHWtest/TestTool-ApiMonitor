package com.apimonitor.httpclient.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * http序列表
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("job_info")
public class JobInfo implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 所属系统
     */
    private String jobGroup;

    /**
     * 主键
     */
    private String jobId;

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

    /**
     * 存档（0-有效，1-删除）
     */
    private Integer archived;


}
