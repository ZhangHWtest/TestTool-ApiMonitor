package com.apimonitor.common.mapper;


import com.apimonitor.common.entity.HttpRequestLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface HttpRequestLogMapper extends BaseMapper<HttpRequestLog> {

	@Delete("delete from http_request_log where createTime < date_sub(curdate(),interval #{day} day)")
	void cleanLogByDay(@Param("day") int day);
}
