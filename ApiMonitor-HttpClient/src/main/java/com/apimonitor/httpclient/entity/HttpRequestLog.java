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
 * http请求日志表
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-19
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
    private Integer requestId;

    /**
     * 父主键（http_sequence表guid）
     */
    private String jobId;

    /**
     * 状态（0-失败；1-成功）
     */
    private Integer status;

    /**
     * 请求耗时
     */
    private Integer costTime;

    /**
     * 响应状态码
     */
    private String statusCode;

    /**
     * 响应结果
     */
    private String responseBody;

    /**
     * 请求日志
     */
    private String log;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
