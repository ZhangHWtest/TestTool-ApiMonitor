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
 * http序列日志表
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("job_info_log")
public class JobInfoLog implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 父主键（job_info表job_id)
     */
    private Integer jobId;

    /**
     * 状态（0-失败；1-成功）
     */
    private Integer status;

    /**
     * 请求耗时
     */
    private Integer costTime;

    /**
     * 请求日志
     */
    private String log;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
