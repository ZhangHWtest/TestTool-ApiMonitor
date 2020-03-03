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
 * http请求表
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("http_request")
public class HttpRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 父主键（即http_sequence表的guid）
     */
    private String pguid;

    /**
     * 主键
     */
    private String guid;

    /**
     * 序号
     */
    private Integer sort;

    /**
     * 地址
     */
    private String url;

    /**
     * HTTP类型（GET, HEAD, POST, PUT, DELETE）
     */
    @TableField("httpMethod")
    private String httpMethod;

    /**
     * 请求头部，格式（key::value\nkey::value）
     */
    private String headers;

    /**
     * 请求参数，格式（key::value\nkey::value）
     */
    private String parameters;

    /**
     * 最大连接时间
     */
    @TableField("maxConnectionSeconds")
    private Integer maxConnectionSeconds;

    /**
     * 结果校验类型（CONTAINS, DOESNT_CONTAIN, STATUSCODE, DEFAULT）
     */
    @TableField("conditionType")
    private String conditionType;

    /**
     * 结果校验内容
     */
    private String condition;

    /**
     * 返回结果的格式（XML, JSON）
     */
    @TableField("resultType")
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
    private Boolean archived;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private LocalDateTime createTime;


}
