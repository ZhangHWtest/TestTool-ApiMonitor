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
 * @since 2020-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("http_sequence")
public class HttpSequence implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 主键
     */
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
     * 是否启动监控（0-不启动，1-启动）
     */
    private Integer enabled;

    /**
     * 监控频率，默认THIRTY30秒
     */
    private String jobCron;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 存档（0:有效，1:删除）
     */
    private Integer archived;


}
