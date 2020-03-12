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
 * http请求日志表
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("http_request_log")
public class HttpRequestLog implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 父id（http_sequence_log表的id）
     */
    private Integer pid;

    /**
     * 父主键（http_sequence表guid）
     */
    private String ppguid;

    /**
     * 父主键（http_request表guid）
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
     * 响应状态码
     */
    @TableField("statusCode")
    private String statusCode;

    /**
     * 响应结果
     */
    @TableField("responseBody")
    private String responseBody;

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
