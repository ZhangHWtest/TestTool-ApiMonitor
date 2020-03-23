package com.apimonitor.httpclient.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.google.gson.JsonArray;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * http请求表
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("http_request")
public class HttpRequest implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 主键
     */
    private String jobId;

    /**
     * 地址
     */
    private String httpUrl;

    /**
     * HTTP类型（GET, HEAD, POST, PUT, DELETE）
     */
    private Integer httpMethod;

    /**
     * 请求头部，格式（key::value\nkey::value）
     */
    private String httpHeaders;

    /**
     * 请求参数，格式（key::value\nkey::value）
     */
    private String httpBody;

    /**
     * 结果校验类型（CONTAINS, DOESNT_CONTAIN, STATUSCODE, DEFAULT）
     */
    private Integer conditionType;

    /**
     * 结果校验内容
     */
    private String conditionBody;

    /**
     * 返回结果的格式（XML, JSON）
     */
    private String resultType;

    /**
     * 变量定义，格式（key::value\nkey::value）
     */
    private String variables;

    /**
     * 备注
     */
    private String remark;

    /**
     * 存档（0-有效，1-删除）
     */
    private Integer archived;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
