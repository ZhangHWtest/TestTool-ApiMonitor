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
 * @since 2020-03-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("http_sequence")
public class HttpSequence implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 主键
     */
    private String guid;

    /**
     * 所属系统
     */
    private String groupName;

    /**
     * 类型（0:单个SINGLE, 1:群组SEQUENCE）
     */
    private Integer type;

    /**
     * 名称
     */
    private String httpName;

    /**
     * 备注
     */
    private String remark;

    /**
     * job名称
     */
    private String jobName;

    /**
     * 是否启动监控（0:未启动，1:运行中）
     */
    private Integer status;

    /**
     * 监控频率，默认THIRTY30秒
     */
    private String frequency;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 存档（0-有效，1-删除）
     */
    private Boolean archived;


}
