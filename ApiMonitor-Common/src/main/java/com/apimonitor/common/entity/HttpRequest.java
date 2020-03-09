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
 * @since 2020-03-09
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
     * 父主键（即http_sequence表的guid）
     */
    private Integer sepId;

    /**
     * 主键
     */
    private Integer gId;

    /**
     * 序号
     */
    private Integer sort;

    /**
     * 地址
     */
    private String httpUrl;

    /**
     * HTTP类型（GET, HEAD, POST, PUT, DELETE）
     */
    private String httpMethod;

    /**
     * 请求头部，格式（key::value\nkey::value）
     */
    private String httpHeaders;

    /**
     * 请求参数，格式（key::value\nkey::value）
     */
    private String httpParameters;

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
    private Boolean archived;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private LocalDateTime createTime;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSepId() {
        return sepId;
    }

    public void setSepId(Integer sepId) {
        this.sepId = sepId;
    }

    public Integer getgId() {
        return gId;
    }

    public void setgId(Integer gId) {
        this.gId = gId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getHttpHeaders() {
        return httpHeaders;
    }

    public void setHttpHeaders(String httpHeaders) {
        this.httpHeaders = httpHeaders;
    }

    public String getHttpParameters() {
        return httpParameters;
    }

    public void setHttpParameters(String httpParameters) {
        this.httpParameters = httpParameters;
    }

    public Integer getMaxConnectionSeconds() {
        return maxConnectionSeconds;
    }

    public void setMaxConnectionSeconds(Integer maxConnectionSeconds) {
        this.maxConnectionSeconds = maxConnectionSeconds;
    }

    public String getConditionType() {
        return conditionType;
    }

    public void setConditionType(String conditionType) {
        this.conditionType = conditionType;
    }

    public String getConditionBody() {
        return conditionBody;
    }

    public void setConditionBody(String conditionBody) {
        this.conditionBody = conditionBody;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getVariables() {
        return variables;
    }

    public void setVariables(String variables) {
        this.variables = variables;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
