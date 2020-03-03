package com.apimonitor.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * http序列日志表
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("http_sequence_log")
public class HttpSequenceLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 父主键（http_sequence表guid）
     */
    private String pguid;

    /**
     * 状态（0-失败；1-成功）
     */
    private Boolean status;

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


}
