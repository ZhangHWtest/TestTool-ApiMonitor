package com.apimonitor.httpclient.mapper;

import com.apimonitor.httpclient.entity.HttpRequestLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * http请求日志表 Mapper 接口
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-19
 */
public interface HttpRequestLogMapper extends BaseMapper<HttpRequestLog> {

    @Delete("delete from http_request_log where createTime < date_sub(curdate(),interval #{day} day)")
    void cleanLogByDay(@Param("day")int day);

}
