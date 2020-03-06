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
 * http请求表
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("http_request")
public class HttpRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * http序列id
     */
    private Integer requestId;

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
    private Integer maxConnectionSeconds;

    /**
     * 结果校验类型（CONTAINS, DOESNT_CONTAIN, STATUSCODE, DEFAULT）
     */
    private String conditionType;

    /**
     * 结果校验内容
     */
    private String condition;

    /**
     * 返回结果的格式（0:XML, 1:JSON）
     */
    private Integer resultType;

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
    private LocalDateTime createTime;


}
