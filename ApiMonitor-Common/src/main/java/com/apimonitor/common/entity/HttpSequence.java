package com.apimonitor.common.entity;

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
 * @since 2020-03-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("http_sequence")
public class HttpSequence implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 系统id
     */
    private Integer systemId;

    /**
     * 接口id
     */
    private Integer requestId;

    /**
     * job群组id
     */
    private String jobGroupId;

    /**
     * job群组名称
     */
    private String jobGroupName;

    /**
     * job_id
     */
    private Integer jobId;

    /**
     * job名称
     */
    private String jobName;

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
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 存档（0-有效，1-删除）
     */
    private Boolean archived;


}
