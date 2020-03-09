package com.apimonitor.common.mapper;

import com.apimonitor.common.entity.HttpRequest;
import com.apimonitor.common.entity.HttpRequestLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HttpRequestMapper extends BaseMapper<HttpRequest> {

	@Select("select t.* from http_request_log t where t.pguid=#{pguid}")
	HttpRequestLog getByGuid(@Param("pguid")String pguid);

	@Select("select t.* from http_request t where t.pguid=#{pguid} order by t.sort")
	List<HttpRequest> getListByPguid(@Param("pguid")String pguid);


}
