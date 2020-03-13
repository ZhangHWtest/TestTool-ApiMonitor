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
 * http请求表
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("api_request")
public class ApiRequest implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 父主键（job_info表job_id）
     */
    private String jobId;

    /**
     * 序号
     */
    private Integer sort;

    /**
     * 地址
     */
    private String apiUrl;

    /**
     * HTTP类型（1:GET,2: HEAD,3: POST,4: PUT,5: DELETE）
     */
    private Integer apiMethod;

    /**
     * 请求头部，格式（key::value\nkey::value）
     */
    private String apiHeaders;

    /**
     * 请求参数，格式（key::value\nkey::value）
     */
    private String apiParameters;

    /**
     * 最大连接时间
     */
    private Integer maxConnectionSeconds;

    /**
     * 结果校验类型（1:CONTAINS,2: DOESNT_CONTAIN,3: STATUSCODE,4: DEFAULT）
     */
    private Integer conditionType;

    /**
     * 结果校验内容
     */
    private String conditionBody;

    /**
     * 返回结果的格式（1:XML, 2:JSON）
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
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 存档（0-有效，1-删除）
     */
    private Boolean archived;


}
